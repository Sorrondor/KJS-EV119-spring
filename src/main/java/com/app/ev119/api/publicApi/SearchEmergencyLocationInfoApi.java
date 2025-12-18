package com.app.ev119.api.publicApi;

import com.app.ev119.domain.dto.ApiResponseDTO;
import com.app.ev119.domain.dto.request.SearchEmergencyLocationInfoRequestDTO;
import com.app.ev119.domain.dto.response.SearchEmergencyLocationInfoResponse;
import com.app.ev119.service.SearchEmergencyLocationInfoService;
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
public class SearchEmergencyLocationInfoApi {

    private final RestClient.Builder builder;
    private final SearchEmergencyLocationInfoService searchEmergencyLocationInfoService;

    @GetMapping("/search-emergency")
    public ResponseEntity<ApiResponseDTO<SearchEmergencyLocationInfoResponse>> getSearchEmergencyLocationInfo(
            @RequestParam("lon") double lon,
            @RequestParam("lat") double lat,
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "numOfRows" , defaultValue = "10") Integer numOfRows
    ) {
        SearchEmergencyLocationInfoRequestDTO req =  new SearchEmergencyLocationInfoRequestDTO();
        req.setWgs84Lon(lon);
        req.setWgs84Lat(lat);
        req.setPageNo(pageNo);
        req.setNumOfRows(numOfRows);

        SearchEmergencyLocationInfoResponse response = searchEmergencyLocationInfoService.getSearchEmergencyLocationInfo(req);

        return ResponseEntity.ok(ApiResponseDTO.of("success", response));
    }



}
