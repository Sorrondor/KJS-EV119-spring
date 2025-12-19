package com.app.ev119.api.privateApi;

import com.app.ev119.domain.dto.ApiResponseDTO;
import com.app.ev119.domain.dto.VisitedDTO;
import com.app.ev119.service.MyPageService;
import com.app.ev119.service.VisitedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-page")
public class VisitedApi {
    private final VisitedService visitedService;
    private final MyPageService myPageService;

    @GetMapping("/visited")
    public ResponseEntity<ApiResponseDTO> getVisited(Authentication tokenDTO) {
        Long memberId = myPageService.findIdByToken(tokenDTO);
        List<VisitedDTO> visitedDTOList = visitedService.findVisitedLogs(memberId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.of("멤버의 방문이력 정보가져오기 성공", visitedDTOList));
    }
    @PostMapping("/visited/add")
    public ResponseEntity<ApiResponseDTO> addVisitedLog(Authentication tokenDTO, @RequestBody VisitedDTO visitedDTO) {
        Long memberId = myPageService.findIdByToken(tokenDTO);
        visitedService.addVisitedLog(memberId, visitedDTO);
        List<VisitedDTO> visitedLogs = visitedService.findVisitedLogs(memberId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.of("멤버의 방문이력 추가 성공",visitedLogs));
    }
    @DeleteMapping("/visited/delete")
    public ResponseEntity<ApiResponseDTO> deleteVisitedLog(Authentication tokenDTO, @RequestBody VisitedDTO visitedDTO) {
        Long memberId = myPageService.findIdByToken(tokenDTO);
        visitedService.removeVisitedLog(memberId, visitedDTO);
        List<VisitedDTO> visitedLogs = visitedService.findVisitedLogs(memberId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.of("멤버의 방문이력 삭제 성공",visitedLogs));
    }
}
