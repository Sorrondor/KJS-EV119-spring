package com.app.ev119.repository;

import com.app.ev119.domain.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
//    단일 복용 약물 추가
//    단일 복용 약물 업데이트
    default void saveMedication(Medication medication){
        save(medication);
    }

//    목록 형태의 복용 약물 추가
//    목록 형태의 복용 약물 업데이트
    default void saveMedications(List<Medication> allergies){
        saveAll(allergies);
    }

//    멤버의 모든 복용 약물 조회
    public List<Medication> findByMember_Id(Long memberId);

//    모든 복용 약물 목록 조회
    default List<Medication> findAllMedications(){
        return findAll();
    }

    //    멤버의 모든 복용 약물 삭제
    public void deleteByMember_Id(Long memberId);
}
