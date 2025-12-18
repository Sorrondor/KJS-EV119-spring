package com.app.ev119.api.publicApi;

import com.app.ev119.domain.dto.ApiResponseDTO;
import com.app.ev119.domain.dto.request.CheckEmergencyRealtimeRequestDTO;
import com.app.ev119.domain.dto.response.CheckEmergencyRealtimeResponse;
import com.app.ev119.service.CheckEmergencyRealtimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/emergency")
@Slf4j
public class CheckEmergencyRealtimeApi {

    private final RestClient.Builder build;

    private final CheckEmergencyRealtimeService  checkEmergencyRealtimeService;

    @GetMapping("/check-realtime")
    public ResponseEntity<ApiResponseDTO<CheckEmergencyRealtimeResponse>> getCheckEmergencyRealtime(
            @RequestParam("stage1") String stage1,
            @RequestParam("stage2") String stage2,
            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(name = "numOfRows", defaultValue = "10") Integer numOfRows
    ) {
        log.info("[Controller] stage1 = " + stage1 + ", stage2 = " + stage2);
        CheckEmergencyRealtimeRequestDTO req = new CheckEmergencyRealtimeRequestDTO();
        req.setStage1(stage1);
        req.setStage2(stage2);
        req.setPageNo(pageNo);
        req.setNumOfRows(numOfRows);

        CheckEmergencyRealtimeResponse response = checkEmergencyRealtimeService.getCheckEmergencyRealtimeResponse(req);


        return ResponseEntity.ok(ApiResponseDTO.of("success", response));

    }
}
