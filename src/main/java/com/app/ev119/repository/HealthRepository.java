package com.app.ev119.repository;

import com.app.ev119.domain.entity.Health;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthRepository extends JpaRepository<Health, Long> {
//    단일 건강 정보 추가
//    단일 건강 정보 업데이트
    default void saveHealth(Health health){
        save(health);
    }

//    멤버의 모든 건강 정보 조회
    public Health findByMember_Id(Long memberId);

//    모든 건강 정보 목록 조회
    default List<Health> findAllHealthList(){
        return findAll();
    }

    //    멤버의 건강 정보 삭제
    public void deleteByMember_Id(Long memberId);
}
