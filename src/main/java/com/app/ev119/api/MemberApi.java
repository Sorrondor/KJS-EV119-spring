package com.app.ev119.api;

import com.app.ev119.domain.dto.ApiResponseDTO;
import com.app.ev119.domain.dto.request.member.LoginRequestDTO;
import com.app.ev119.domain.dto.request.member.SignUpRequestDTO;
import com.app.ev119.domain.dto.response.member.LoginResponseDTO;
import com.app.ev119.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
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
    public ResponseEntity<ApiResponseDTO> logout(@RequestParam Long memberId) {
        memberService.logout(memberId);
        return ResponseEntity.ok(ApiResponseDTO.of("SUCCESS"));
    }
}
