package com.app.ev119.repository;

import com.app.ev119.domain.entity.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllergyRepository extends JpaRepository<Allergy, Long> {
//    단일 알러지 추가
//    단일 알러지 업데이트
    default void saveAllergy(Allergy allergy){
        save(allergy);
    }

    //    목록 형태의 알러지 추가
//    목록 형태의 알러지 업데이트
    default void saveAllergies(List<Allergy> allergies){
        saveAll(allergies);
    }

    //    멤버의 모든 알러지 조회
    public List<Allergy> findByMember_Id(Long memberId);

    //    모든 알러지 목록 조회
    default List<Allergy> findAllAllergies(){
        return findAll();
    }

    //    멤버의 모든 알러지 삭제
    public void deleteByMember_Id(Long memberId);
}
