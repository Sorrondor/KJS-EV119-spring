package com.app.ev119.service.member;

import com.app.ev119.domain.dto.request.member.LoginRequestDTO;
import com.app.ev119.domain.dto.request.member.SignUpRequestDTO;
import com.app.ev119.domain.dto.response.member.LoginResponseDTO;
import com.app.ev119.domain.entity.Member;
import com.app.ev119.jwt.JwtTokenProvider;
import com.app.ev119.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final StringRedisTemplate stringRedisTemplate; // 문자열용 템플릿만 사용

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

        return new LoginResponseDTO(accessToken, refreshToken);
    }

    @Transactional
    public void logout(Long memberId) {
        // RT:memberId 키 삭제
        stringRedisTemplate.delete("RT:" + memberId);
    }
}
