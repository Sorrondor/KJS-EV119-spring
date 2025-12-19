package com.app.ev119.api.privateApi;

import com.app.ev119.domain.dto.ApiResponseDTO;
import com.app.ev119.domain.dto.HealthDTO;
import com.app.ev119.service.HealthService;
import com.app.ev119.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-page")
public class HealthApi {
    private final MyPageService myPageService;
    private final HealthService healthService;

    @GetMapping("/health")
    public ResponseEntity<ApiResponseDTO> getHealth(Authentication tokenDTO) {
        Long memberId = myPageService.findIdByToken(tokenDTO);
        HealthDTO memberHealth = healthService.findHealth(memberId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.of("멤버의 건강 정보 가져오기 성공", memberHealth));
    }

    @PostMapping("/health/modify")
    public ResponseEntity<ApiResponseDTO> updateHealth(Authentication tokenDTO, HealthDTO healthDTO) {
        Long memberId = myPageService.findIdByToken(tokenDTO);
        healthService.updateHealth(memberId, healthDTO);
        HealthDTO resultHealthDTO = healthService.findHealth(memberId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.of("멤버의 건강 정보 추가 성공", resultHealthDTO));
    }

    @PostMapping("/health/add-disease")
    public ResponseEntity<ApiResponseDTO> addDisease(Authentication tokenDTO, String diseaseName) {
        Long memberId = myPageService.findIdByToken(tokenDTO);
        healthService.addDisease(memberId, diseaseName);
        HealthDTO healthDTO = healthService.findHealth(memberId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.of("멤버의 기저질환 추가", healthDTO));
    }
}
