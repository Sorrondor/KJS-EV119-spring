package com.app.ev119.service;

import com.app.ev119.domain.dto.HealthDTO;

public interface HealthService {
//    건강정보 조회 / 수정
    public HealthDTO findHealth(Long memberId);
    public void updateHealth(Long memberId, HealthDTO healthDTO);
    public void addDisease(Long memberId, String diseaseName);
}
