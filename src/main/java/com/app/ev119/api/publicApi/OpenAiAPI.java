package com.app.ev119.api.publicApi;

import com.app.ev119.domain.dto.ApiResponseDTO;
import com.app.ev119.service.openAPI.OpenApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/bot-api")
public class OpenAiAPI {
    private final OpenApiService openApiService;

    @PostMapping("/bot-json")
    public ResponseEntity<ApiResponseDTO> chat(@RequestParam String message) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("open Ai api로부터 응답을 받았습니다." ,openApiService.searchFirstAid(message)));

    }
}
