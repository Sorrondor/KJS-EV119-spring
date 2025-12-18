package com.app.ev119.api.publicApi;

import com.app.ev119.domain.dto.ApiResponseDTO;
import com.app.ev119.domain.dto.request.CheckAvailabilityIllPatientsRequestDTO;
import com.app.ev119.domain.dto.response.CheckAvailabilityIllPatientsResponse;
import com.app.ev119.service.CheckAvailabilityIllPatientsService;
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
public class CheckAvailabilityIllPatientsApi {

    private final RestClient.Builder builder;
    private final CheckAvailabilityIllPatientsService checkAvailabilityIllPatientsService;

    @GetMapping("/check-availity")
    public ResponseEntity<ApiResponseDTO<CheckAvailabilityIllPatientsResponse>> getCHeckAvailabilityIllPatients(
            @RequestParam("stage1") String stage1,
            @RequestParam("stage2") String stage2,
            @RequestParam("smTown") String smTown,
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "numOfRows", defaultValue = "10") Integer numOfRows
    ){
        CheckAvailabilityIllPatientsRequestDTO req =  new CheckAvailabilityIllPatientsRequestDTO();
        req.setStage1(stage1);
        req.setStage2(stage2);
        req.setSmTown(smTown);
        req.setPageNo(pageNo);
        req.setNumOfRows(numOfRows);

        CheckAvailabilityIllPatientsResponse response = checkAvailabilityIllPatientsService.getCheckAvailabilityIllPatients(req);

        return ResponseEntity.ok(ApiResponseDTO.of("SUCCESS", response));
    }
}
