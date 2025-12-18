package com.app.ev119.service;

import com.app.ev119.domain.dto.request.EmergencyRoomMessageRequestDTO;
import com.app.ev119.domain.dto.response.CheckEmergencyRealtimeResponse;
import com.app.ev119.domain.dto.response.EmergencyRoomMessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmergencyRoomMessageService {

    private final RestTemplate restTemplate;

    @Value("${api.emergency.service-key}")
    private String serviceKey;

    @Value("${api.emergency.emergency-trauma-message-url}")
    private String emergencyRoomMessageUrl;

    public EmergencyRoomMessageResponse getEmergencyRoomMessage(EmergencyRoomMessageRequestDTO req) {
        String url = UriComponentsBuilder
                .fromHttpUrl(emergencyRoomMessageUrl)
                .queryParam("serviceKey", serviceKey)
                .queryParam("pageNo", req.getPageNo())
                .queryParam("numOfRows", req.getNumOfRows())
                .queryParam("HPID", req.getHpid())
                .queryParam("QN", req.getQn())
                .queryParam("Q0", req.getQ0())
                .queryParam("Q1", req.getQ1())
                .build(false)
                .toUriString();


        String xml = restTemplate.getForObject(url, String.class);
        log.info("URL = {}", url);
        log.info("RAW XML = {}", xml);

        return restTemplate.getForObject(url, EmergencyRoomMessageResponse.class);
    }
}
