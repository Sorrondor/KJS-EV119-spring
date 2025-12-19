package com.app.ev119.service;

import com.app.ev119.domain.dto.MedicationDTO;
import com.app.ev119.domain.entity.Medication;
import com.app.ev119.domain.entity.Member;
import com.app.ev119.repository.MedicationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor @Transactional(rollbackFor = Exception.class)
public class MedicationServiceImpl implements MedicationService {
    @PersistenceContext
    private EntityManager entityManager;
    private final MedicationRepository medicationRepository;

    @Override
    public List<MedicationDTO> findMedications(Long memberId) {
        List<Medication> medicationList = medicationRepository.findByMember_Id(memberId);

        List<MedicationDTO> medicationDTOList = medicationList.stream().map((medication) -> {
            MedicationDTO medicationDTO = new MedicationDTO();
            medicationDTO.setMedicationName(medication.getMedicationName());
            medicationDTO.setMedicationUsage(medication.getMedicationUsage());
            medicationDTO.setMedicationTakingtime(medication.getMedicationTakingtime());
            medicationDTO.setMemberId(memberId);
            return medicationDTO;
        }).toList();

        return medicationDTOList;
    }

    @Override
    public void modifyMedications(Long memberId, List<MedicationDTO> medicationDTOs) {
        Member member = entityManager.find(Member.class, memberId);
        List<Medication> medicationList = medicationDTOs.stream().map((medicationDTO -> {
            Medication medication = new Medication();
            if(medicationDTO.getId() != null) {
                medication.setId(medicationDTO.getId());
            }
            medication.setMember(member);
            medication.setMedicationName(medicationDTO.getMedicationName());
            medication.setMedicationUsage(medicationDTO.getMedicationUsage());
            medication.setMedicationTakingtime(medicationDTO.getMedicationTakingtime());
            medicationRepository.saveMedication(medication);
            return medication;
        })).toList();

        member.setMedications(medicationList);
    }
}
