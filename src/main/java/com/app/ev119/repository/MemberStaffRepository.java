package com.app.ev119.repository;

import com.app.ev119.domain.entity.MemberStaff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberStaffRepository extends JpaRepository<MemberStaff, Long> {
//    단일 의료 자격 추가
//    단일 의료 자격 업데이트
    default void saveMemberStaff(MemberStaff memberStaff){
        save(memberStaff);
    }

//    목록 형태의 의료 자격 추가
//    목록 형태의 의료 자격 업데이트
    default void saveMemberStaffs(List<MemberStaff> allergies){
        saveAll(allergies);
    }

//    멤버의 모든 의료 자격 조회
    public List<MemberStaff> findByMember_Id(Long memberId);

//    모든 의료 자격 목록 조회
    default List<MemberStaff> findAllMemberStaffs(){
        return findAll();
    }

//    멤버의 모든 의료 자격 삭제
    public void deleteByMember_Id(Long memberId);
}
