package com.app.ev119.service;

import com.app.ev119.domain.dto.EmergencyPhoneDTO;
import com.app.ev119.domain.entity.EmergencyPhone;
import com.app.ev119.domain.entity.Member;
import com.app.ev119.exception.EmergencyPhoneException;
import com.app.ev119.exception.MyPageException;
import com.app.ev119.repository.EmergencyPhoneRepository;
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
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EmergencyPhoneServiceImpl implements EmergencyPhoneService {
    @PersistenceContext
    private EntityManager entityManager;
    private final EmergencyPhoneRepository emergencyPhoneRepository;

    @Override
    public List<EmergencyPhoneDTO> findEmergencyPhones(Long memberId) {
        if (memberId == null) {
            throw new MyPageException("회원 ID가 없습니다.");
        }
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
        if (member == null) {
            throw new MyPageException("존재하지 않는 회원입니다. memberId: " + memberId);
        }

        if (emergencyPhoneDTOs == null) {
            EmergencyPhone emergencyPhone = new EmergencyPhone();
            emergencyPhone.setMember(member);
            entityManager.persist(emergencyPhone);
            entityManager.flush();
//            throw new EmergencyPhoneException("응급 전화 정보가 없습니다.");
        }

        List<EmergencyPhone> existingPhones = emergencyPhoneRepository.findByMember_Id(memberId);
        Set<Long> existingIds = existingPhones.stream()
                .map(EmergencyPhone::getId)
                .collect(Collectors.toSet());

        Set<Long> incomingIds = emergencyPhoneDTOs.stream()
                .map(EmergencyPhoneDTO::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        List<EmergencyPhone> toDelete = existingPhones.stream()
                .filter(phone -> !incomingIds.contains(phone.getId()))
                .toList();

        for (EmergencyPhone phone : toDelete) {
            emergencyPhoneRepository.delete(phone);
        }

        List<EmergencyPhone> phoneList = emergencyPhoneDTOs.stream().map(phoneDTO -> {
            EmergencyPhone phone;

            if (phoneDTO.getId() != null && existingIds.contains(phoneDTO.getId())) {
                phone = emergencyPhoneRepository.findById(phoneDTO.getId())
                        .orElse(new EmergencyPhone());
            } else {
                phone = new EmergencyPhone();
            }

            phone.setMember(member);
            phone.setEmergencyPhoneName(phoneDTO.getEmergencyPhoneName());
            phone.setEmergencyPhoneRelationship(phoneDTO.getEmergencyPhoneRelationship());
            phone.setEmergencyPhoneNumber(phoneDTO.getEmergencyPhoneNumber());
            emergencyPhoneRepository.saveEmergencyPhone(phone);

            return phone;
        }).toList();

        member.setEmergencyPhones(phoneList);
    }

}
