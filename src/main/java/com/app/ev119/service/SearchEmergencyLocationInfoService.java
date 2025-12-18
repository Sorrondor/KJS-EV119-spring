package com.app.ev119.service;

import com.app.ev119.domain.dto.request.SearchEmergencyLocationInfoRequestDTO;
import com.app.ev119.domain.dto.response.SearchEmergencyLocationInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class SearchEmergencyLocationInfoService {

    private final RestTemplate restTemplate;

    @Value("${api.emergency.service-key}")
    private String serviceKey;

    @Value("${api.emergency.search-emergency-location-url}")
    private String searchEmergencyLocationUrl;

    public SearchEmergencyLocationInfoResponse getSearchEmergencyLocationInfo(SearchEmergencyLocationInfoRequestDTO req) {
        String url = UriComponentsBuilder
                .fromHttpUrl(searchEmergencyLocationUrl)
                .queryParam("serviceKey", serviceKey)
                .queryParam("WGS84_LON", req.getWgs84Lon())
                .queryParam("WGS84_LAT", req.getWgs84Lat())
                .queryParam("pageNo", req.getPageNo())
                .queryParam("numOfRows", req.getNumOfRows())
                .toUriString();

        return restTemplate.getForObject(url, SearchEmergencyLocationInfoResponse.class);
    }
}
