package com.app.ev119.service;

import com.app.ev119.domain.dto.request.CheckAvailabilityIllPatientsRequestDTO;
import com.app.ev119.domain.dto.response.CheckAvailabilityIllPatientsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Slf4j
public class CheckAvailabilityIllPatientsService {

    private final RestTemplate restTemplate;

    @Value("${api.emergency.service-key}")
    private String serviceKey;

    @Value("${api.emergency.check-availability-url}")
    private String checkAvailabilityIllPatientsUrl;

    public CheckAvailabilityIllPatientsResponse getCheckAvailabilityIllPatients(CheckAvailabilityIllPatientsRequestDTO req) {
        String url = UriComponentsBuilder
                .fromHttpUrl(checkAvailabilityIllPatientsUrl)
                .queryParam("serviceKey", serviceKey)
                .queryParam("STAGE1", req.getStage1())
                .queryParam("STAGE2", req.getStage2())
                .queryParam("SM_TYPE", req.getSmTown())
                .queryParam("pageNo", req.getPageNo())
                .queryParam("numOfRows", req.getNumOfRows())
                .build(false)
                .toUriString();

        String xml = restTemplate.getForObject(url, String.class);
        log.info("URL = {}", url);
        log.info("RAW XML = {}", xml);

        return restTemplate.getForObject(url, CheckAvailabilityIllPatientsResponse.class);

    }

}
