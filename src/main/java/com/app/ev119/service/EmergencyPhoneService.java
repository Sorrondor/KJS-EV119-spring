package com.app.ev119.service;

import com.app.ev119.domain.dto.EmergencyPhoneDTO;

import java.util.List;

public interface EmergencyPhoneService {
//    응급 연락처
    public List<EmergencyPhoneDTO> findEmergencyPhones(Long memberId);
    public void modifyEmergencyPhones(Long memberId, List<EmergencyPhoneDTO> emergencyPhoneDTOs);
}
