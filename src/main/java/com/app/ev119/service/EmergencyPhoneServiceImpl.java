package com.app.ev119.service;

import com.app.ev119.domain.dto.EmergencyPhoneDTO;
import com.app.ev119.domain.entity.EmergencyPhone;
import com.app.ev119.domain.entity.Member;
import com.app.ev119.repository.EmergencyPhoneRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EmergencyPhoneServiceImpl implements EmergencyPhoneService {
    @PersistenceContext
    private EntityManager entityManager;
    private final EmergencyPhoneRepository emergencyPhoneRepository;

    @Override
    public List<EmergencyPhoneDTO> findEmergencyPhones(Long memberId) {
        List<EmergencyPhone> emergencyPhoneList = emergencyPhoneRepository.findByMember_Id(memberId);

        List<EmergencyPhoneDTO> emergencyPhoneDTOList = emergencyPhoneList.stream().map((data) -> {
            EmergencyPhoneDTO emergencyPhoneDTO = new EmergencyPhoneDTO();
            emergencyPhoneDTO.setId(data.getId());
            emergencyPhoneDTO.setEmergencyPhoneName(data.getEmergencyPhoneName());
            emergencyPhoneDTO.setMemberId(data.getMember().getId());
            emergencyPhoneDTO.setEmergencyPhoneNumber(data.getEmergencyPhoneNumber());
            emergencyPhoneDTO.setEmergencyPhoneRelationship(data.getEmergencyPhoneRelationship());
            return emergencyPhoneDTO;
        }).toList();
        return emergencyPhoneDTOList;
    }

    @Override
    public void modifyEmergencyPhones(Long memberId, List<EmergencyPhoneDTO> emergencyPhoneDTOs) {
        Member member = entityManager.find(Member.class, memberId);
        List<EmergencyPhone> emergencyPhoneList = emergencyPhoneDTOs.stream().map((emergencyPhoneDTO -> {
            EmergencyPhone emergencyPhone = new EmergencyPhone();
            if(emergencyPhoneDTO.getId() != null) {
                emergencyPhone = entityManager.find(EmergencyPhone.class, emergencyPhoneDTO.getId());
            }
            emergencyPhone.setMember(member);
            emergencyPhone.setEmergencyPhoneName(emergencyPhoneDTO.getEmergencyPhoneName());
            emergencyPhone.setEmergencyPhoneNumber(emergencyPhoneDTO.getEmergencyPhoneNumber());
            emergencyPhone.setEmergencyPhoneRelationship(emergencyPhoneDTO.getEmergencyPhoneRelationship());
            entityManager.persist(emergencyPhone);
            return emergencyPhone;
        })).toList();
        member.setEmergencyPhones(emergencyPhoneList);
    }

}
