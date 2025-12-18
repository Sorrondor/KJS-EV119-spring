package com.app.ev119.service;

import com.app.ev119.domain.dto.request.SearchEmergencyMedicalInfoRequestDTO;
import com.app.ev119.domain.dto.response.SearchEmergencyMedicalInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchEmergencyMedicalInfoService {

    private final RestTemplate restTemplate;

    @Value("${api.emergency.service-key}")
    private String serviceKey;

    @Value("${api.emergency.search-emergency-list-url}")
    private String searchEmergencyListUrl;

    public SearchEmergencyMedicalInfoResponse getSearchEmergencyMedicalInfo(SearchEmergencyMedicalInfoRequestDTO req) {

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(searchEmergencyListUrl)
                .queryParam("serviceKey", serviceKey)
                .queryParam("Q0", req.getQ0())
                .queryParam("Q1", req.getQ1())
                .queryParam("pageNo", req.getPageNo())
                .queryParam("numOfRows", req.getNumOfRows());

        if (req.getQt() != null && !req.getQt().isBlank()) builder.queryParam("QT", req.getQt());
        if (req.getQz() != null && !req.getQz().isBlank()) builder.queryParam("QZ", req.getQz());
        if (req.getQd() != null && !req.getQd().isBlank()) builder.queryParam("QD", req.getQd());
        if (req.getQn() != null && !req.getQn().isBlank()) builder.queryParam("QN", req.getQn());
        if (req.getOrd() != null && !req.getOrd().isBlank()) builder.queryParam("ORD", req.getOrd());

        String url = builder.build(false).toUriString();
        log.info("URL = {}", url);

        SearchEmergencyMedicalInfoResponse response =
                restTemplate.getForObject(url, SearchEmergencyMedicalInfoResponse.class);


        if (response != null
                && response.getBody() != null
                && response.getBody().getItems() == null) {
            response.getBody().setItems(Collections.emptyList());
        }

        return response;
    }
}
