package com.app.ev119.api.publicApi;

import com.app.ev119.domain.dto.ApiResponseDTO;
import com.app.ev119.domain.dto.request.TraumaCenterBasicInfoRequestDTO;
import com.app.ev119.domain.dto.response.TraumaCenterBasicInfoResponse;
import com.app.ev119.service.TraumaCenterBasicInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/emergency")
public class TraumaCenterBasicInfoApi {

    private final RestClient.Builder  clientBuilder;
    private final TraumaCenterBasicInfoService traumaCenterBasicInfoService;

    @GetMapping("/trauma-center-basic")
    public ResponseEntity<ApiResponseDTO<TraumaCenterBasicInfoResponse>> getTraumaCenterBasicInfo(
            @RequestParam("serviceKey") String serviceKey,
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "numOfRows", defaultValue = "10") Integer numOfRows,
            @RequestParam("hpid") String hpid
    ) {
        TraumaCenterBasicInfoRequestDTO req = new TraumaCenterBasicInfoRequestDTO();
        req.setServiceKey(serviceKey);
        req.setPageNo(pageNo);
        req.setNumOfRows(numOfRows);
        req.setHpid(hpid);

        TraumaCenterBasicInfoResponse response = traumaCenterBasicInfoService.getTraumaBasicInfo(req);

        return ResponseEntity.ok(ApiResponseDTO.of("success", response));
    }
}
