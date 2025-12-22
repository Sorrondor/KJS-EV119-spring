package com.app.ev119.service.member;

import com.app.ev119.domain.dto.request.member.LoginRequestDTO;
import com.app.ev119.domain.dto.request.member.MemberStaffSignUpRequestDTO;
import com.app.ev119.domain.dto.request.member.SignUpRequestDTO;
import com.app.ev119.domain.dto.response.member.LoginResponseDTO;
import com.app.ev119.domain.entity.Member;
import com.app.ev119.domain.entity.MemberStaff;
import com.app.ev119.domain.type.MemberType;
import com.app.ev119.domain.type.StaffStatus;
import com.app.ev119.jwt.JwtTokenProvider;
import com.app.ev119.repository.MemberRepository;
import com.app.ev119.repository.MemberStaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MemberService {

    @Value("${jwt.token-blacklist-prefix}")
    private String BLACKLIST_TOKEN_PREFIX;

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final StringRedisTemplate stringRedisTemplate;
    private final RedisTemplate<Object, Object> redisTemplate;
    private final MemberStaffRepository memberStaffRepository;


    @Transactional
    public void signUp(SignUpRequestDTO dto) {

        // 이메일 중복 체크
        if (memberRepository.existsByMemberEmail(dto.getMemberEmail())) {
            log.info("중복된 이메일: {}", dto.getMemberEmail());
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }


        // validateDuplicatePhone(dto.getMemberPhone());

        Member member = new Member();
        member.setMemberEmail(dto.getMemberEmail());
        member.setMemberPassword(passwordEncoder.encode(dto.getMemberPassword()));
        member.setMemberName(dto.getMemberName());
        member.setMemberPhone(dto.getMemberPhone());
        member.setMemberType(MemberType.MEMBER);

        memberRepository.save(member);
    }


    @Transactional
    public void signUpStaff(MemberStaffSignUpRequestDTO req) {

        if (memberRepository.existsByMemberEmail(req.getMemberEmail())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }


        // validateDuplicatePhone(req.getMemberPhone());

        if (memberStaffRepository.existsByLicenseNumber(req.getLicenseNumber())) {
            throw new IllegalArgumentException("이미 등록된 면허번호 입니다.");
        }


        Member member = new Member();
        member.setMemberEmail(req.getMemberEmail());
        member.setMemberName(req.getMemberName());
        member.setMemberPassword(passwordEncoder.encode(req.getMemberPassword()));
        member.setMemberPhone(req.getMemberPhone());
        member.setMemberType(MemberType.STAFF);

        memberRepository.save(member);


        MemberStaff memberStaff = new MemberStaff();
        memberStaff.setMember(member);
        memberStaff.setLicenseNumber(req.getLicenseNumber());
        memberStaff.setMemberStaffIndustry(req.getMemberStaffIndustry());
        memberStaff.setStaffStatus(StaffStatus.PENDING);

        memberStaffRepository.save(memberStaff);
    }


    @Transactional
    public LoginResponseDTO login(LoginRequestDTO dto) {

        Member member = memberRepository.findByMemberEmail(dto.getMemberEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        if (!passwordEncoder.matches(dto.getMemberPassword(), member.getMemberPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        Long memberId = member.getId();

        if(member.getMemberType() == MemberType.STAFF){
            MemberStaff memberStaff = memberStaffRepository.findFirstByMember_Id(memberId)
                    .orElseThrow(() -> new IllegalArgumentException("의료진 신청 정보가 없습니다."));

            if(memberStaff.getStaffStatus() != StaffStatus.APPROVED) {
                throw new IllegalArgumentException("의료진 승인 대기 중입니다.");
            }

        }

        // AccessToken 발급
        String role = "ROLE_" + member.getMemberType().name();
        String accessToken = jwtTokenProvider.createAccessToken(memberId, role);
        String refreshToken = jwtTokenProvider.createRefreshToken(memberId);

        Long refreshValidityMs = jwtTokenProvider.getRefreshTokenValidityInMs();

        // Redis에 refresh token 저장 (RT:memberId)
        stringRedisTemplate.opsForValue()
                .set("RT:" + memberId, refreshToken, refreshValidityMs, TimeUnit.MILLISECONDS);

        return new LoginResponseDTO(
                member.getId(),
                member.getMemberName(),
                member.getMemberEmail(),
                accessToken,
                refreshToken
        );
    }


    @Transactional
    public void logout(Long memberId, String refreshToken) {
        if (memberId == null) {
            log.warn("로그아웃 요청: memberId가 null입니다.");
            throw new IllegalStateException("로그인 상태가 아닙니다.");
        }

        // RT 삭제
        stringRedisTemplate.delete("RT:" + memberId);
        log.info("로그아웃: Redis에서 RT:{} 삭제 완료", memberId);

        // 전달된 refreshToken 블랙리스트 처리
        if (refreshToken != null && !refreshToken.isBlank()) {
            long remainingMs = jwtTokenProvider.getRemindValidityInMs(refreshToken);
            addBlacklist(refreshToken, remainingMs);
        }
    }


    @Transactional
    public LoginResponseDTO refreshToken(String refreshToken) {

        // 블랙리스트 확인
        if (isBlacklisted(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 refresh token입니다.");
        }

        // Jwt 유효성 검증
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new IllegalArgumentException("만료되었거나 위조된 refresh token 입니다.");
        }

        // 토큰에서 memberId 조회
        Long memberId = jwtTokenProvider.getMemberId(refreshToken);

        // redis에 저장된 refresh token과 일치 확인
        String storedRefreshToken = stringRedisTemplate.opsForValue().get("RT:" + memberId);

        if (storedRefreshToken == null || !storedRefreshToken.equals(refreshToken)) {
            throw new IllegalArgumentException("이미 무효화된 refresh token 입니다.");
        }

        // 회원 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (member.getMemberType() == MemberType.STAFF) {

            MemberStaff staff = memberStaffRepository.findFirstByMember_Id(memberId)
                    .orElseThrow(() -> new IllegalStateException("의료진 신청 정보가 없습니다."));

            if (staff.getStaffStatus() != StaffStatus.APPROVED) {
                throw new IllegalStateException("의료진 승인 상태가 아닙니다.");
            }
        }


        // 새로운 토큰 발급
        String role = "ROLE_" + member.getMemberType().name();
        String newAccessToken = jwtTokenProvider.createAccessToken(memberId, role);
        String newRefreshToken = jwtTokenProvider.createRefreshToken(memberId);

        // 기존 refreshToken을 블랙리스트 처리
        long remainingMs = jwtTokenProvider.getRemindValidityInMs(refreshToken);
        addBlacklist(refreshToken, remainingMs);

        // redis에 새 refresh token 저장
        Long newRefreshValidityMs = jwtTokenProvider.getRefreshTokenValidityInMs();
        stringRedisTemplate.opsForValue()
                .set("RT:" + memberId, newRefreshToken, newRefreshValidityMs, TimeUnit.MILLISECONDS);

        return new LoginResponseDTO(
                member.getId(),
                member.getMemberEmail(),
                member.getMemberName(),
                newAccessToken,
                newRefreshToken
        );
    }


    public void addBlacklist(String refreshToken, long expirationMs) {
        String key = BLACKLIST_TOKEN_PREFIX + refreshToken;
        redisTemplate.opsForValue().set(key, "blacklisted", expirationMs, TimeUnit.MILLISECONDS);
    }

    public boolean isBlacklisted(String refreshToken) {
        if (refreshToken == null) return true;

        String key = BLACKLIST_TOKEN_PREFIX + refreshToken;
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public void validateDuplicatePhone(String memberPhone) {
        if (memberRepository.findByMemberPhone(memberPhone).isPresent()) {
            throw new IllegalArgumentException("이미 등록된 전화번호입니다.");
        }
    }

    private String normalizePhone(String memberPhone) {
        if (memberPhone == null) return null;
        return memberPhone.replaceAll("\\D", "");
    }


    @Transactional
    public void resetPassword(String resetToken, String newPassword) {
        if (resetToken == null || resetToken.isBlank()) {
            throw new IllegalArgumentException("resetToken이 없습니다.");
        }
        if (newPassword == null || newPassword.isBlank()) {
            throw new IllegalArgumentException("새 비밀번호를 입력해주세요.");
        }
        if (newPassword.length() < 8) {
            throw new IllegalArgumentException("비밀번호는 최소 8자 이상이어야 합니다.");
        }

        String key = "RESET_PW:" + resetToken;

        String memberPhoneFromRedis = stringRedisTemplate.opsForValue().get(key);
        if (memberPhoneFromRedis == null || memberPhoneFromRedis.isBlank()) {
            throw new IllegalArgumentException("resetToken이 만료되었거나 유효하지 않습니다.");
        }

        String normalizedPhone = normalizePhone(memberPhoneFromRedis);

        Member member = memberRepository.findByMemberPhone(normalizedPhone)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        member.setMemberPassword(passwordEncoder.encode(newPassword));

        stringRedisTemplate.delete(key);
    }

    @Transactional(readOnly = true)
    public String findMemberEmailByMemberPhone(String memberPhone) {
        String normalizedPhone = normalizePhone(memberPhone);

        return memberRepository.findEmailByMemberPhone(normalizedPhone)
                .orElseThrow(() -> new IllegalArgumentException("해당 번호로 가입된 회원이 없습니다."));
    }


    @Transactional
    public void withdraw(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        memberRepository.delete(member);
    }
}
