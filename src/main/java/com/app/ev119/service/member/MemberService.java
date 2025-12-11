package com.app.ev119.service.member;

import com.app.ev119.domain.dto.request.member.LoginRequestDTO;
import com.app.ev119.domain.dto.request.member.SignUpRequestDTO;
import com.app.ev119.domain.dto.response.member.LoginResponseDTO;
import com.app.ev119.domain.entity.Member;
import com.app.ev119.jwt.JwtTokenProvider;
import com.app.ev119.repository.MemberRepository;
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

    @Transactional
    public void signUp(SignUpRequestDTO dto) {

        // 이메일 중복 체크
        if (memberRepository.existsByMemberEmail(dto.getMemberEmail())) {
            log.info("중복된 이메일: {}", dto.getMemberEmail());
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        Member member = new Member();
        member.setMemberEmail(dto.getMemberEmail());
        member.setMemberPassword(passwordEncoder.encode(dto.getMemberPassword()));
        member.setMemberName(dto.getMemberName());
        member.setMemberPhone(dto.getMemberPhone());


        memberRepository.save(member);
    }

    @Transactional
    public LoginResponseDTO login(LoginRequestDTO dto) {

        Member member = memberRepository.findByMemberEmail(dto.getMemberEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        if (!passwordEncoder.matches(dto.getMemberPassword(), member.getMemberPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        Long memberId = member.getId();

        String accessToken = jwtTokenProvider.createAccessToken(memberId);
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

        stringRedisTemplate.delete("RT:" + memberId);
        log.info("로그아웃: Redis에서 RT:{} 삭제 완료", memberId);

        if (refreshToken != null) {
            log.info("클라이언트에서 전달된 refreshToken={}", refreshToken);
            long remainingMs = jwtTokenProvider.getRemindValidityInMs(refreshToken);
            addBlacklist(refreshToken, remainingMs);
        }


    }

    public boolean isBlacklistedRefreshToken(LoginResponseDTO tokenDTO) {
        if (tokenDTO == null || tokenDTO.getRefreshToken() == null) {
            return true;
        }

        Long memberId = tokenDTO.getMemberId();
        String refreshToken = tokenDTO.getRefreshToken();

        String key = BLACKLIST_TOKEN_PREFIX + memberId;

        try {
            Boolean exists = redisTemplate.opsForSet().isMember(key, refreshToken);
            return Boolean.TRUE.equals(exists);
        } catch (Exception e) {
            log.error("Redis error while checking blacklist", e);
            return true;
        }
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

    public LoginResponseDTO refreshToken(String refreshToken) {

        // 블랙리스트 확인 여부
        if(isBlacklisted(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 refresh token입니다.");
        }

        // Jwt 유효성 검증
        if(!jwtTokenProvider.validateToken(refreshToken)) {
            throw new IllegalArgumentException("만료되었거나 위조된 refresh token 입니다.");
        }

        // 토큰에서 memberId 조회
        Long memberId = jwtTokenProvider.getMemberId(refreshToken);

        //redis에 저장된 refresh token과 일치 불일치 확인
        String storedRefreshToken = stringRedisTemplate.opsForValue().get("RT:" + memberId);

        if(storedRefreshToken == null || !storedRefreshToken.equals(refreshToken)) {
            throw new IllegalArgumentException("이미 무효화된 refresh token 입니다.");
        }

        // 새로운 토큰 발급
        String newAccessToken =  jwtTokenProvider.createAccessToken(memberId);
        String newRefreshToken = jwtTokenProvider.createRefreshToken(memberId);

        // 기존 refresh token은 블랙리스트 처리
        Long remainingMs = jwtTokenProvider.getRemindValidityInMs(refreshToken);
        addBlacklist(newRefreshToken, remainingMs);

        //redis에 새 refresh token 저장
        Long newRefreshValidityMs = jwtTokenProvider.getRefreshTokenValidityInMs();

        stringRedisTemplate.opsForValue()
                .set("RT:" + memberId, newRefreshToken, newRefreshValidityMs, TimeUnit.MILLISECONDS);

        // 회원 정보 조회 후 DTO 리턴
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        return new LoginResponseDTO(
                member.getId(),
                member.getMemberEmail(),
                member.getMemberName(),
                newAccessToken,
                newRefreshToken
        );
    }

}
