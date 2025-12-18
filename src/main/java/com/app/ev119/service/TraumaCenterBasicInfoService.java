package com.app.ev119.service;

import com.app.ev119.domain.dto.request.TraumaCenterBasicInfoRequestDTO;
import com.app.ev119.domain.dto.response.TraumaCenterBasicInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Slf4j
public class TraumaCenterBasicInfoService {

    private final RestTemplate restTemplate;

    @Value("${api.emergency.service-key}")
    private String serviceKey;

    @Value("${api.emergency.search-trauma-basic-url}")
    private String searchTraumaBasicUrl;

    public TraumaCenterBasicInfoResponse getTraumaBasicInfo(TraumaCenterBasicInfoRequestDTO req) {
        String url = UriComponentsBuilder
                .fromHttpUrl(searchTraumaBasicUrl)
                .queryParam("serviceKey", serviceKey)
                .queryParam("pageNo", req.getPageNo())
                .queryParam("numOfRows", req.getNumOfRows())
                .queryParam("hpid", req.getHpid())
                .toUriString();

        return restTemplate.getForObject(url, TraumaCenterBasicInfoResponse.class);


    }
}
