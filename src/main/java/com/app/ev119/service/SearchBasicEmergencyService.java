package com.app.ev119.service;

import com.app.ev119.domain.dto.request.SearchBasicEmergencyRequestDTO;
import com.app.ev119.domain.dto.response.SearchBasicEmergencyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchBasicEmergencyService {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${api.emergency.service-key}")
    private String serviceKey;

    @Value("${api.emergency.search-emergency-basic-url}")
    private String searchEmergencyBasicUrl;

    public SearchBasicEmergencyResponse getSearchBasicEmergency(SearchBasicEmergencyRequestDTO req) {
        String url = UriComponentsBuilder
                .fromHttpUrl(searchEmergencyBasicUrl)
                .queryParam("serviceKey", serviceKey)
                .queryParam("HPid", req.getHpid())
                .queryParam("pageNo", req.getPageNo())
                .queryParam("numOfRows", req.getNumOfRows())
                .build(false)
                .toUriString();

        String xml = restTemplate.getForObject(url, String.class);
        log.info("URL = {}", url);
        log.info("RAW XML = {}", xml);

        return restTemplate.getForObject(url, SearchBasicEmergencyResponse.class);
    }
}
