package com.app.ev119.api.publicApi;

import com.app.ev119.domain.dto.ApiResponseDTO;
import com.app.ev119.domain.dto.request.SearchEmergencyMedicalInfoRequestDTO;
import com.app.ev119.domain.dto.response.SearchEmergencyMedicalInfoResponse;
import com.app.ev119.service.SearchEmergencyMedicalInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/emergency")
public class SearchEmergencyMedicalInfoApi {

    private final RestClient.Builder clientBuilder;
    private final SearchEmergencyMedicalInfoService searchEmergencyMedicalInfoService;

    @GetMapping("search-emergency-list")
    public ResponseEntity<ApiResponseDTO<SearchEmergencyMedicalInfoResponse>> getSearchEmergencyMedicalInfo(
            @RequestParam("Q0") String q0,
            @RequestParam("Q1") String q1,
            @RequestParam("QT") String qt,
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "numOfRows", defaultValue = "10") Integer numOfRows
    ) {
        SearchEmergencyMedicalInfoRequestDTO req =  new SearchEmergencyMedicalInfoRequestDTO();
        req.setQ0(q0);
        req.setQ1(q1);
        req.setQt(qt);
        req.setPageNo(pageNo);
        req.setNumOfRows(numOfRows);

        SearchEmergencyMedicalInfoResponse response =  searchEmergencyMedicalInfoService.getSearchEmergencyMedicalInfo(req);

        return ResponseEntity.ok(ApiResponseDTO.of("SUCCESS", response));
    }
}
