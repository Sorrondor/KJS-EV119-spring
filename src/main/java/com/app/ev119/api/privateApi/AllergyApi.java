package com.app.ev119.api.privateApi;

import com.app.ev119.domain.dto.AllergyDTO;
import com.app.ev119.domain.dto.ApiResponseDTO;
import com.app.ev119.service.AllergyService;
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
public class AllergyApi {
    private final AllergyService allergyService;
    private final MyPageService myPageService;

    @GetMapping("/allergy")
    public ResponseEntity<ApiResponseDTO> getAllAllergy(Authentication tokenDTO) {
        Long memberId = myPageService.findIdByToken(tokenDTO);
        List<AllergyDTO> memberAllergies = allergyService.findAllergies(memberId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.of("멤버의 알러지 정보가져오기 성공",memberAllergies));
    }
    @PostMapping("/allergy/modify")
    public ResponseEntity<ApiResponseDTO> modifyAllergy(Authentication tokenDTO, @RequestBody List<AllergyDTO> allergyDTOs) {
        Long memberId = myPageService.findIdByToken(tokenDTO);
        allergyService.modifyAllergies(memberId, allergyDTOs);
        List<AllergyDTO> allergies = allergyService.findAllergies(memberId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.of("멤버의 알러지 추가 성공", allergies));
    }
}
