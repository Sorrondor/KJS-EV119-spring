package com.app.ev119.api.publicApi;

import com.app.ev119.domain.dto.ApiResponseDTO;
import com.app.ev119.domain.dto.request.SearchBasicEmergencyRequestDTO;
import com.app.ev119.domain.dto.request.SearchEmergencyLocationInfoRequestDTO;
import com.app.ev119.domain.dto.response.SearchBasicEmergencyResponse;
import com.app.ev119.service.SearchBasicEmergencyService;
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
@RequestMapping("/api/emergency")
@Slf4j
public class SearchBasicEmergencyApi {

    private final RestClient.Builder clientBuilder;
    private final SearchBasicEmergencyService searchBasicEmergencyService;

    @GetMapping("/search-basic-emergency")
    public ResponseEntity<ApiResponseDTO<SearchBasicEmergencyResponse>> getSearchBasicEmergency(
            @RequestParam("HPID") String hpid,
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "numOfRows", defaultValue = "10") Integer numOfRows
    ) {
        SearchBasicEmergencyRequestDTO req = new SearchBasicEmergencyRequestDTO();
        req.setHpid(hpid);
        req.setPageNo(pageNo);
        req.setNumOfRows(numOfRows);

        SearchBasicEmergencyResponse response = searchBasicEmergencyService.getSearchBasicEmergency(req);

        return ResponseEntity.ok(ApiResponseDTO.of("SUCCESS", response));
    }
}
