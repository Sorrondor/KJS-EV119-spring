package com.app.ev119.handler;

import com.app.ev119.domain.entity.Member;
import com.app.ev119.jwt.JwtTokenProvider;
import com.app.ev119.oauth2.OAuth2MemberInfo;
import com.app.ev119.oauth2.OAuth2MemberInfoFactory;
import com.app.ev119.repository.MemberRepository;
import com.app.ev119.service.member.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    
    private final JwtTokenProvider jwtTokenProvider;
    private MemberRepository memberRepository;
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException {
        log.info("OAuth2로그인 성공 authentication:{}", authentication);

        //OAuth2Member, provider 꺼낸다.
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = oauthToken.getPrincipal();

        String resistrationId = oauthToken.getAuthorizedClientRegistrationId();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        //Provider 별로 attributes 파싱
        OAuth2MemberInfo oauth2MemberInfo = OAuth2MemberInfoFactory.of(resistrationId, attributes);

        String email = oauth2MemberInfo.getEmail();
        log.info("oauth2 로그인 성공 email:{}", email);

        //DB에서 member 찾기
        Member member = memberRepository.findByMemberEmail(email).orElseThrow(() -> new IllegalArgumentException("소셜로그인이 회원 DB에 없습니다."));

        //JWT발급
        String accessToken = jwtTokenProvider.createAccessToken(member.getId());
        String refreshToken = jwtTokenProvider.createRefreshToken(member.getId());

        Long refreshTokenExpireMiles = jwtTokenProvider.getRefreshTokenValidityInMs();

        //Refresh token -> redis에 저장
        stringRedisTemplate.opsForValue()
                .set("RT:" + member.getMemberEmail(), refreshToken, refreshTokenExpireMiles);

        //Refresh token -> cookie에 저장
        ResponseCookie responseCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(refreshTokenExpireMiles / 1000)
                .sameSite("None")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());

        //Access token은 프론트에 저장
        String targetUrl = UriComponentsBuilder.fromUriString(request.getRequestURI()).build().toUriString();

        log.info("targetUrl:{}", targetUrl);

        getRedirectStrategy().sendRedirect(request, response, targetUrl);


    }
}
