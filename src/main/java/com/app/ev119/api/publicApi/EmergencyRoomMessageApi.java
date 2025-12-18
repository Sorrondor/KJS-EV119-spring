package com.app.ev119.api.publicApi;

import com.app.ev119.domain.dto.ApiResponseDTO;
import com.app.ev119.domain.dto.request.EmergencyRoomMessageRequestDTO;
import com.app.ev119.domain.dto.response.CheckEmergencyRealtimeResponse;
import com.app.ev119.domain.dto.response.EmergencyRoomMessageResponse;
import com.app.ev119.service.EmergencyRoomMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/emergency")
public class EmergencyRoomMessageApi {

    private final RestClient.Builder builder;
    private final EmergencyRoomMessageService emergencyRoomMessageService;

    @GetMapping("/emergency-message")
    public ResponseEntity<ApiResponseDTO<EmergencyRoomMessageResponse>> getEmergencyRoomMessage(
            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(name = "numOfRows", defaultValue = "10") Integer numOfRows,
            @RequestParam("Q0") String q0,
            @RequestParam("Q1") String q1,
            @RequestParam(name = "HPID", required = false) String hpid,
            @RequestParam(name = "QN", required = false) String qn
    ) {
        EmergencyRoomMessageRequestDTO req = new EmergencyRoomMessageRequestDTO();
        req.setPageNo(pageNo);
        req.setNumOfRows(numOfRows);
        req.setQ0(q0);
        req.setQ1(q1);
        req.setHpid(hpid);
        req.setQn(qn);

        EmergencyRoomMessageResponse response = emergencyRoomMessageService.getEmergencyRoomMessage(req);
        return ResponseEntity.ok(ApiResponseDTO.of("SUCCESS", response));
    }


}
