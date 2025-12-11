package com.app.ev119.config;

import com.app.ev119.domain.dto.response.member.LoginResponseDTO;
import com.app.ev119.handler.JwtAuthenticationEntryPoint;
import com.app.ev119.handler.OAuth2LoginSuccessHandler;
import com.app.ev119.jwt.JwtAuthenticationFilter;
import com.app.ev119.service.member.CustomOAuth2UserService;
import com.app.ev119.service.member.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    private final MemberService memberService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())      // CSRF 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/private/**").authenticated()
                        .anyRequest().permitAll() // 모든 요청 허용
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(oAuth2LoginSuccessHandler))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("http://localhost:3000/login")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            HttpSession session = request.getSession(false);
                            if(session != null) {
                                session.invalidate();
                            }

                            //  리프레쉬 토큰을 블랙리스트에 추가
                            Cookie[] cookies = request.getCookies();
                            if(cookies != null) {
                                for(Cookie cookie : cookies) {
                                    if(cookie.getName().equals("refreshToken")) {
                                        String refreshToken = cookie.getValue();
                                        LoginResponseDTO tokenDTO = new LoginResponseDTO();
                                        tokenDTO.setRefreshToken(refreshToken);
                                        memberService.isBlacklistedRefreshToken(tokenDTO);
                                    }
                                }
                            }

                            ResponseCookie expired = ResponseCookie.from("refreshToken", "")
                                    .path("")
                                    .httpOnly(true)
                                    .maxAge(0)
                                    .build();
                            response.addHeader(HttpHeaders.SET_COOKIE, expired.toString());
                            response.sendRedirect("http://localhost:3000");
                        })
                        .permitAll()
                );

        return http.build();
    }

    // Cors 설정
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); // React 앱 주소
        configuration.addAllowedMethod("*"); // 모든 HTTP 메서드 허용
        configuration.addAllowedHeader("*"); // 모든 요청 헤더 허용
        configuration.setAllowCredentials(true); // 인증 정보 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 적용
        return source;
    }




}
