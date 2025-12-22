package com.app.ev119.service;

import com.app.ev119.domain.dto.MedicationDTO;
import com.app.ev119.domain.entity.Medication;
import com.app.ev119.domain.entity.Member;
import com.app.ev119.exception.MedicationException;
import com.app.ev119.exception.MyPageException;
import com.app.ev119.repository.MedicationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor @Transactional(rollbackFor = Exception.class)
public class MedicationServiceImpl implements MedicationService {
    @PersistenceContext
    private EntityManager entityManager;
    private final MedicationRepository medicationRepository;

    @Override
    public List<MedicationDTO> findMedications(Long memberId) {
        if (memberId == null) {
            throw new MyPageException("회원 ID가 없습니다.");
        }
        List<Medication> medicationList = medicationRepository.findByMember_Id(memberId);

        List<MedicationDTO> medicationDTOList = medicationList.stream().map((medication) -> {
            MedicationDTO medicationDTO = new MedicationDTO();
            medicationDTO.setId(medication.getId());
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
        if (member == null) {
            throw new MyPageException("존재하지 않는 회원입니다. memberId: " + memberId);
        }

        if (medicationDTOs == null) {
            Medication medication = new Medication();
            medication.setMember(member);
            entityManager.persist(medication);
            entityManager.flush();
//            throw new MedicationException("복용 약물 정보가 없습니다.");
        }

        List<Medication> existingMedications = medicationRepository.findByMember_Id(memberId);
        Set<Long> existingIds = existingMedications.stream()
                .map(Medication::getId)
                .collect(Collectors.toSet());

        Set<Long> incomingIds = medicationDTOs.stream()
                .map(MedicationDTO::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        List<Medication> toDelete = existingMedications.stream()
                .filter(med -> !incomingIds.contains(med.getId()))
                .toList();

        for (Medication medication : toDelete) {
            medicationRepository.delete(medication);
        }

        List<Medication> medicationList = medicationDTOs.stream().map(medicationDTO -> {
            Medication medication;

            if (medicationDTO.getId() != null && existingIds.contains(medicationDTO.getId())) {
                medication = medicationRepository.findById(medicationDTO.getId())
                        .orElse(new Medication());
            } else {
                medication = new Medication();
            }

            medication.setMember(member);
            medication.setMedicationName(medicationDTO.getMedicationName());
            medication.setMedicationUsage(medicationDTO.getMedicationUsage());
            medication.setMedicationTakingtime(medicationDTO.getMedicationTakingtime());
            medicationRepository.saveMedication(medication);

            return medication;
        }).toList();

        member.setMedications(medicationList);
    }
}
