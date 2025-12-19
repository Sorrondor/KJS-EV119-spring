package com.app.ev119.api.privateApi;

import com.app.ev119.domain.dto.ApiResponseDTO;
import com.app.ev119.domain.dto.MedicationDTO;
import com.app.ev119.service.MedicationService;
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
public class MedicationApi {
    private final MyPageService myPageService;
    private final MedicationService medicationService;

    @GetMapping("/medication")
    public ResponseEntity<ApiResponseDTO> getAllMedication(Authentication tokenDTO) {
        Long memberId = myPageService.findIdByToken(tokenDTO);
        List<MedicationDTO> memberMedication = medicationService.findMedications(memberId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.of("멤버의 복용약물 정보 가져오기 성공",memberMedication));
    }

    @PostMapping("/medication/modify")
    public ResponseEntity<ApiResponseDTO> modifyMedication(Authentication tokenDTO, @RequestBody List<MedicationDTO> medicationDTOs) {
        Long memberId = myPageService.findIdByToken(tokenDTO);
        medicationService.modifyMedications(memberId, medicationDTOs);
        List<MedicationDTO> medications = medicationService.findMedications(memberId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.of("멤버의 복용약물 추가 성공",medications));
    }
}