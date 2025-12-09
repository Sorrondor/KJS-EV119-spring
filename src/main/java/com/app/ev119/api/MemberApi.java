package com.app.ev119.api;

import com.app.ev119.domain.dto.ApiResponseDTO;
import com.app.ev119.domain.dto.request.member.LoginRequestDTO;
import com.app.ev119.domain.dto.request.member.SignUpRequestDTO;
import com.app.ev119.domain.dto.response.member.LoginResponseDTO;
import com.app.ev119.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberApi {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDTO> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        memberService.signUp(signUpRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.of("회원가입이 완료되었습니다"));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {

        LoginResponseDTO loginResponse = memberService.login(loginRequestDTO);

        String refreshToken = loginResponse.getRefreshToken();

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .path("/")
                .maxAge(60L * 60 * 24 * 7)
                .sameSite("Lax")
                .build();

        Map<String, String> data = Map.of(
                "accessToken", loginResponse.getAccessToken()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                .body(ApiResponseDTO.of("로그인이 성공했습니다", data));
    }


    // 로그아웃
    @DeleteMapping("/logout")
    public ResponseEntity<ApiResponseDTO> logout(
            @CookieValue(name = "refreshToken", required = false) String refreshToken
    ) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication.getPrincipal().equals("anonymousUser")) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponseDTO.of("로그인 상태가 아닙니다."));
        }

        Long memberId = (Long) authentication.getPrincipal();
        memberService.logout(memberId, refreshToken);

        ResponseCookie deleteCookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, deleteCookie.toString())
                .body(ApiResponseDTO.of("로그아웃 되었습니다."));
    }

}
