package com.app.ev119.service;

import com.app.ev119.domain.dto.DiseaseDTO;
import com.app.ev119.domain.dto.HealthDTO;
import com.app.ev119.domain.entity.Disease;
import com.app.ev119.domain.entity.Health;
import com.app.ev119.domain.entity.Member;
import com.app.ev119.exception.DiseaseException;
import com.app.ev119.exception.HealthException;
import com.app.ev119.exception.MyPageException;
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
        if (health == null) {
            health = new Health();
            Member member = entityManager.find(Member.class, memberId);
            health.setMember(member);
            entityManager.persist(health);
            entityManager.flush();
//            throw new HealthException("건강 정보를 찾을 수 없습니다. memberId: " + memberId);
        }

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
        if (healthDTO == null) {
            throw new HealthException("수정할 건강 정보가 없습니다.");
        }
        Member member = entityManager.find(Member.class, memberId);
        if (member == null) {
            throw new MyPageException("존재하지 않는 회원입니다. memberId: " + memberId);
        }
        Health health = healthRepository.findByMember_Id(memberId);
        if (health == null) {
            throw new HealthException("건강 정보를 찾을 수 없습니다. memberId: " + memberId);
        }
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
        if (diseaseName == null || diseaseName.isEmpty()) {
            throw new DiseaseException("질병명이 비어있습니다.");
        }
        Health health = healthRepository.findByMember_Id(memberId);
        if (health == null) {
            throw new HealthException("건강 정보를 찾을 수 없습니다. memberId: " + memberId);
        }

        Disease disease = new Disease();
        disease.setDiseaseName(diseaseName);
        disease.setHealth(health);
        diseaseRepository.saveDisease(disease);

        List<Disease> diseaseList = diseaseRepository.findByHealth_Id(health.getId());

        health.setDiseases(diseaseList);
        healthRepository.saveHealth(health);
    }

    @Override
    public void removeDisease(Long memberId, DiseaseDTO diseaseDTO){
        if (diseaseDTO == null) {
            throw new DiseaseException("삭제할 질병 정보가 없습니다.");
        }
        Health health = healthRepository.findByMember_Id(memberId);
        if (health == null) {
            throw new HealthException("건강 정보를 찾을 수 없습니다. memberId: " + memberId);
        }

        if (diseaseDTO.getId() != null) {
            Disease disease = diseaseRepository.findById(diseaseDTO.getId())
                    .orElseThrow(() -> new DiseaseException("삭제할 질병을 찾을 수 없습니다."));
            
            if (disease.getHealth() != null && disease.getHealth().getId().equals(health.getId())) {
                diseaseRepository.delete(disease);
            } else {
                throw new DiseaseException("해당 건강정보의 질병이 아닙니다.");
            }
        } else {
            if (diseaseDTO.getDiseaseName() != null && !diseaseDTO.getDiseaseName().isEmpty()) {
                List<Disease> diseases = diseaseRepository.findByHealth_Id(health.getId());
                Disease targetDisease = diseases.stream()
                        .filter(d -> d.getDiseaseName().equals(diseaseDTO.getDiseaseName()))
                        .findFirst()
                        .orElseThrow(() -> new DiseaseException("삭제할 질병을 찾을 수 없습니다."));
                
                diseaseRepository.delete(targetDisease);
            } else {
                throw new DiseaseException("질병 정보가 올바르지 않습니다.");
            }
        }

        List<Disease> updatedDiseases = diseaseRepository.findByHealth_Id(health.getId());
        health.setDiseases(updatedDiseases);
        healthRepository.saveHealth(health);
    }
}
