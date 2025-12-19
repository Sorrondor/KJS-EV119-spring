package com.app.ev119.repository;

import com.app.ev119.domain.entity.StaffCert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffCertRepository extends JpaRepository<StaffCert, Long> {
//    단일 인증 정보 추가
//    단일 인증 정보 업데이트
    default void saveStaffCert(StaffCert staffCert){
        save(staffCert);
    }

//    목록 형태의 인증 정보 추가
//    목록 형태의 인증 정보 업데이트
    default void saveStaffCerts(List<StaffCert> allergies){
        saveAll(allergies);
    }

//    멤버의 모든 인증 정보 조회
    public List<StaffCert> findByMemberStaff_Id(Long memberStaffId);

//    모든 인증 정보 목록 조회
    default List<StaffCert> findAllStaffCerts(){
        return findAll();
    }

//    멤버의 모든 인증 정보 삭제
    public void deleteByMemberStaff_Id(Long memberStaffId);
}
