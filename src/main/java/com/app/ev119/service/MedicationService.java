package com.app.ev119.service;

import com.app.ev119.domain.dto.MedicationDTO;

import java.util.List;

public interface MedicationService {
//    복용 약물
    public List<MedicationDTO> findMedications(Long memberId);
    public void modifyMedications(Long memberId, List<MedicationDTO> medicationDTOs);
}
