package com.app.ev119.repository;

import com.app.ev119.domain.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
//    단일 질병 추가
//    단일 질병 업데이트
    default void saveDisease(Disease disease){
        save(disease);
    }

//    목록 형태의 질병 추가
//    목록 형태의 질병 업데이트
    default void saveAllergies(List<Disease> allergies){
        saveAll(allergies);
    }

//    건강 정보의 모든 질병 조회
    public List<Disease> findByHealth_Id(Long memberId);

//    모든 질병 목록 조회
    default List<Disease> findAllHealth(){
        return findAll();
    }

//    건강 정보의 모든 질병 삭제
    public void deleteByHealth_Id(Long healthId);
}
