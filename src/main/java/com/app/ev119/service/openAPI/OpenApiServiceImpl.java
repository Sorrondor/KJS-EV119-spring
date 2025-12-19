package com.app.ev119.service.openAPI;

import com.app.ev119.domain.dto.response.firstAid.FirstAidResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OpenApiServiceImpl implements OpenApiService {

    @Value("${openai.api-key}")
    private String openaiApiKey;

    private final RestTemplate restTemplate;

    @Override
    public Map<String, Object> searchFirstAid(String message) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openaiApiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-4o-mini");
        body.put("messages", List.of(
                Map.of("role", "system", "content", """
                    JSON만 출력.
                    대처는 응급 상황에서 현장에서 응급처치로 할 수 있는 조치만
                    필드: firstAidKeywords(string[]), urgency("medium"|"high"|"critical"), firstAidProcedures(string[5])
                    설명문 없이 오직 JSON만 출력.
                    firstAidKeywords 사용자의 입력 값에서 핵심 키워드만 (예: '숨을 안 쉰다' -> 호흡 관란, 숨 등) 뽑아 배열로 출력.
                    한글자만 받을 시에는 가장 근접한 단어로 검색
                """),
                Map.of("role", "user", "content", message)
        ));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            Map response = restTemplate.postForObject(
                    "https://api.openai.com/v1/chat/completions",
                    entity,
                    Map.class
            );

            List choices = (List) response.get("choices");
            Map firstChoice = (Map) choices.get(0);
            Map messageObj = (Map) firstChoice.get("message");
            String content = (String) messageObj.get("content");

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> finalJson = mapper.readValue(content, Map.class);

            return finalJson;

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "OpenAI API 요청 실패");
            errorResponse.put("apiKey", openaiApiKey);
            errorResponse.put("details", e.getMessage());

            return errorResponse;
        }

    }
}
