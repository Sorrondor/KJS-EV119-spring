package com.app.ev119.service.openAPI;

import com.app.ev119.domain.dto.response.firstAid.FirstAidResponseDTO;

import java.util.Map;

public interface OpenApiService {
    public Map<String, Object> searchFirstAid(String message);
}
