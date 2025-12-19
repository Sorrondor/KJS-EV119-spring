package com.app.ev119.service;

import com.app.ev119.domain.dto.DiseaseDTO;
import com.app.ev119.domain.dto.HealthDTO;
import com.app.ev119.domain.entity.Disease;
import com.app.ev119.domain.entity.Health;
import com.app.ev119.domain.entity.Member;
import com.app.ev119.repository.DiseaseRepository;
import com.app.ev119.repository.HealthRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class HealthServiceImpl implements HealthService {

    @PersistenceContext
    private EntityManager entityManager;

    private final HealthRepository healthRepository;
    private final DiseaseRepository diseaseRepository;

    @Override
    public HealthDTO findHealth(Long memberId) {
        Health health = healthRepository.findByMember_Id(memberId);

        HealthDTO healthDTO = new HealthDTO();
        healthDTO.setId(health.getId());
        healthDTO.setHealthBloodRh(health.getHealthBloodRh());
        healthDTO.setHealthGender(health.getHealthGender());
        healthDTO.setHealthWeight(health.getHealthWeight());
        healthDTO.setHealthHeight(health.getHealthHeight());
        healthDTO.setHealthBloodAbo(health.getHealthBloodAbo());
        healthDTO.setMemberId(health.getId());

        List<Disease> diseases = diseaseRepository.findByHealth_Id(health.getId());
        List<DiseaseDTO> diseaseDTOList = diseases.stream().map((disease -> {
            DiseaseDTO diseaseDTO = new DiseaseDTO();
            diseaseDTO.setHealthId(healthDTO.getId());
            diseaseDTO.setDiseaseName(disease.getDiseaseName());
            diseaseDTO.setId(disease.getId());
            return diseaseDTO;
        })).toList();

        healthDTO.setDiseases(diseaseDTOList);

        return healthDTO;
    }

    @Override
    public void updateHealth(Long memberId, HealthDTO healthDTO) {
        Member member = entityManager.find(Member.class, memberId);
        Health health = healthRepository.findByMember_Id(memberId);
        health.setHealthBloodRh(healthDTO.getHealthBloodRh());
        health.setHealthBloodAbo(healthDTO.getHealthBloodAbo());
        health.setHealthGender(healthDTO.getHealthGender());
        health.setHealthWeight(healthDTO.getHealthWeight());
        health.setHealthHeight(healthDTO.getHealthHeight());
        health.setMember(member);

        healthRepository.saveHealth(health);

        member.setHealth(health);
        entityManager.merge(member);
        entityManager.flush();
    }

    @Override
    public void addDisease(Long memberId, String diseaseName) {
        Health health = healthRepository.findByMember_Id(memberId);

        Disease disease = new Disease();
        disease.setDiseaseName(diseaseName);
        disease.setHealth(health);
        diseaseRepository.saveDisease(disease);

        List<Disease> diseaseList = diseaseRepository.findByHealth_Id(health.getId());

        health.setDiseases(diseaseList);
        healthRepository.saveHealth(health);
    }

}
