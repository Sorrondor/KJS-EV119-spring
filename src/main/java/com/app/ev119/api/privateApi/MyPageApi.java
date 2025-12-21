package com.app.ev119.api.privateApi;

import com.app.ev119.domain.dto.ApiResponseDTO;
import com.app.ev119.domain.dto.request.MemberDTO;
import com.app.ev119.domain.dto.response.ChangePasswordDTO;
import com.app.ev119.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-page")
public class MyPageApi {
    private final MyPageService myPageService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponseDTO<MemberDTO>> getProfile(Authentication tokenDTO){
        Long memberId = myPageService.findIdByToken(tokenDTO);
        MemberDTO member = myPageService.getMember(memberId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.of("멤버의 정보 가져오기 성공", member));
    }

    @PutMapping("/edit")
    public ResponseEntity<ApiResponseDTO> editProfile(Authentication tokenDTO, @RequestBody MemberDTO member) {
        Long memberId = myPageService.findIdByToken(tokenDTO);
        myPageService.modifyMember(memberId, member);
        MemberDTO memberDTO = myPageService.getMember(memberId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.of("멤버의 정보 수정하기 성공", memberDTO));
    }

    @PostMapping("/change-password")
    public ResponseEntity<ApiResponseDTO> changePassword(Authentication tokenDTO, @RequestBody ChangePasswordDTO password) {
        Long memberId = myPageService.findIdByToken(tokenDTO);
        myPageService.modifyPassword(memberId, password);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.of("멤버의 비밀번호 변경 성공"));
    }

    @DeleteMapping("/unregister")
    public ResponseEntity<ApiResponseDTO> removeMember(Authentication tokenDTO){
        Long memberId = myPageService.findIdByToken(tokenDTO);
        myPageService.removeMember(memberId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ApiResponseDTO.of("멤버의 회원 탈퇴 성공"));
    }
}
