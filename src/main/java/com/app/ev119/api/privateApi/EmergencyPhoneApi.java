package com.app.ev119.api.privateApi;

import com.app.ev119.domain.dto.ApiResponseDTO;
import com.app.ev119.domain.dto.EmergencyPhoneDTO;
import com.app.ev119.service.EmergencyPhoneService;
import com.app.ev119.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-page")
public class EmergencyPhoneApi {
    private final EmergencyPhoneService emergencyPhoneService;
    private final MyPageService myPageService;

    @GetMapping("/emergency-phone")
    public ResponseEntity<ApiResponseDTO> getEmergencyPhone(Authentication tokenDTO) {
        Long memberId = myPageService.findIdByToken(tokenDTO);
        List<EmergencyPhoneDTO> emergencyPhoneDTO = emergencyPhoneService.findEmergencyPhones(memberId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.of("멤버의 건강 정보가져오기 성공",emergencyPhoneDTO));
    }
    @PostMapping("/emergency-phone/modify")
    public ResponseEntity<ApiResponseDTO> modifyEmergencyPhone(Authentication tokenDTO, @RequestBody List<EmergencyPhoneDTO> emergencyPhoneDTOs) {
        Long memberId = myPageService.findIdByToken(tokenDTO);
        emergencyPhoneService.modifyEmergencyPhones(memberId, emergencyPhoneDTOs);
        List<EmergencyPhoneDTO> emergencyPhoneDTOList = emergencyPhoneService.findEmergencyPhones(memberId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.of("멤버의 알러지 추가 성공", emergencyPhoneDTOList));
    }
}
