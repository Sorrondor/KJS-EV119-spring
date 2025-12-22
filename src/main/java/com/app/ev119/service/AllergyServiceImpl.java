package com.app.ev119.service;

import com.app.ev119.domain.dto.AllergyDTO;
import com.app.ev119.domain.entity.Allergy;
import com.app.ev119.domain.entity.Member;
import com.app.ev119.exception.MyPageException;
import com.app.ev119.repository.AllergyRepository;
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
public class AllergyServiceImpl implements AllergyService {

    @PersistenceContext
    private EntityManager entityManager;

    private final AllergyRepository allergyRepository;

    @Override
    public List<AllergyDTO> findAllergies(Long memberId) {
        if (memberId == null) {
            throw new MyPageException("회원 ID가 없습니다.");
        }
        List<Allergy> allergies = allergyRepository.findByMember_Id(memberId);
        List<AllergyDTO> allergyDTOs = allergies.stream().map((data) -> {
            AllergyDTO allergyDTO = new AllergyDTO();
            allergyDTO.setId(data.getId());
            allergyDTO.setAllergyName(data.getAllergyName());
            allergyDTO.setAllergyType(data.getAllergyType());
            allergyDTO.setMemberId(data.getMember().getId());
            return allergyDTO;
        }).toList();
        return allergyDTOs;
    }

    @Override
    public void modifyAllergies(Long memberId, List<AllergyDTO> allergyDTOs) {
        Member member = entityManager.find(Member.class, memberId);
        if (member == null) {
            throw new MyPageException("존재하지 않는 회원입니다. memberId: " + memberId);
        }

        if (allergyDTOs == null) {
            Allergy allergy = new Allergy();
            allergy.setMember(member);
            entityManager.persist(allergy);
            entityManager.flush();
//            throw new AllergyException("알러지 정보가 없습니다.");
        }

        List<Allergy> existingAllergies = allergyRepository.findByMember_Id(memberId);
        Set<Long> existingIds = existingAllergies.stream()
                .map(Allergy::getId)
                .collect(Collectors.toSet());

        Set<Long> incomingIds = allergyDTOs.stream()
                .map(AllergyDTO::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        List<Allergy> toDelete = existingAllergies.stream()
                .filter(allergy -> !incomingIds.contains(allergy.getId()))
                .toList();

        for (Allergy allergy : toDelete) {
            allergyRepository.delete(allergy);
        }

        List<Allergy> allergyList = allergyDTOs.stream().map(allergyDTO -> {
            Allergy allergy;

            if (allergyDTO.getId() != null && existingIds.contains(allergyDTO.getId())) {
                allergy = allergyRepository.findById(allergyDTO.getId())
                        .orElse(new Allergy());
            } else {
                allergy = new Allergy();
            }

            allergy.setMember(member);
            allergy.setAllergyName(allergyDTO.getAllergyName());
            allergy.setAllergyType(allergyDTO.getAllergyType());
            allergyRepository.saveAllergy(allergy);

            return allergy;
        }).toList();

        member.setAllergies(allergyList);
    }
}
