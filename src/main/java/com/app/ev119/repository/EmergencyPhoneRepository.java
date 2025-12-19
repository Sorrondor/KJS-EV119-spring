package com.app.ev119.repository;

import com.app.ev119.domain.entity.EmergencyPhone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmergencyPhoneRepository extends JpaRepository<EmergencyPhone, Long> {
//    단일 응급 전화 추가
//    단일 응급 전화 업데이트
    default void saveEmergencyPhone(EmergencyPhone emergencyPhone){
        save(emergencyPhone);
    }

//    목록 형태의 응급 전화 추가
//    목록 형태의 응급 전화 업데이트
    default void saveEmergencyPhones(List<EmergencyPhone> allergies){
        saveAll(allergies);
    }

//    멤버의 모든 응급 전화 조회
    public List<EmergencyPhone> findByMember_Id(Long memberId);

//    모든 응급 전화 목록 조회
    default List<EmergencyPhone> findAllEmergencyPhones(){
        return findAll();
    }

//    멤버의 모든 응급 전화 삭제
    public void deleteByMember_Id(Long memberId);
}
