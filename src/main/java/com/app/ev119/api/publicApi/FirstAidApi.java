package com.app.ev119.api.publicApi;

import com.app.ev119.domain.dto.ApiResponseDTO;
import com.app.ev119.domain.dto.request.firstAid.FirstAidRequestDTO;
import com.app.ev119.domain.dto.response.firstAid.FirstAidResponseDTO;
import com.app.ev119.domain.type.UrgencyType;
import com.app.ev119.service.FirstAid.FirstAidKeywordService;
import com.app.ev119.service.FirstAid.FirstAidService;
import com.app.ev119.service.openAPI.OpenApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/first-aid")
public class FirstAidApi {

    private final FirstAidService firstAidService;
    private final FirstAidKeywordService firstAidKeywordService;
    private final OpenApiService openApiService;

    @GetMapping("/load-aid")
    public ResponseEntity<ApiResponseDTO> loadAid(@RequestParam String message){

        if (firstAidKeywordService.existFirstAidKeyword(message)){
            FirstAidResponseDTO data = firstAidService.getFirstAid(message);
            log.info("data = {}", data);
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("데이터 베이스 내 응급처치 사항을 불러왔습니다.", data));
        } else {
            Map<String, Object> openApiResponse = openApiService.searchFirstAid(message);
            UrgencyType urgency = null;
            if(openApiResponse.get("urgency").equals("HIGH") || openApiResponse.get("urgency").equals("high")){
                urgency = UrgencyType.HIGH;
            } else if(openApiResponse.get("urgency").equals("CRITICAL") || openApiResponse.get("urgency").equals("critical")) {
                urgency = UrgencyType.CRITICAL;
            } else {
                urgency = UrgencyType.MEDIUM;
            }
            FirstAidRequestDTO firstAidRequestDTO = new FirstAidRequestDTO();
            firstAidRequestDTO.setUrgency(urgency);
            firstAidRequestDTO.setFirstAidKeywords((List<String>)openApiResponse.get("firstAidKeywords"));
            firstAidRequestDTO.setFirstAidProcedures((List<String>)openApiResponse.get("firstAidProcedures"));

            FirstAidResponseDTO firstAidResponseDTO = firstAidService.firstAidSave(firstAidRequestDTO);

            return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("Open AI 응급처치 사항을 불러왔습니다.", firstAidResponseDTO));
        }
    }
}
