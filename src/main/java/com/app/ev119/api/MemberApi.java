package com.app.ev119.api;


import com.app.ev119.domain.dto.ApiResponseDTO;
import com.app.ev119.domain.dto.request.MemberDTO;
import com.app.ev119.domain.dto.request.member.LoginRequestDTO;
import com.app.ev119.domain.dto.request.member.SignUpRequestDTO;
import com.app.ev119.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberApi {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDTO> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        memberService.signUp(signUpRequestDTO);
        return ResponseEntity.ok(ApiResponseDTO.of("SUCCESS"));
    }

    // 로그인
    @GetMapping("/login")
    public ResponseEntity<ApiResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        memberService.login(loginRequestDTO);
        return ResponseEntity.ok(ApiResponseDTO.of("SUCCESS"));
    }

    //로그아웃
    @DeleteMapping("/logout")
    public ResponseEntity<ApiResponseDTO> logout(@RequestParam Long memberId) {
        memberService.logout(memberId);
        return ResponseEntity.ok(ApiResponseDTO.of("SUCCESS"));
    }
}
