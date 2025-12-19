package com.app.ev119.repository;

import com.app.ev119.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 회원 조회
    public Optional<Member> findById(Long id);

    // 전체 조회
    public List<Member> findAll();

    // 이메일로 회원 찾기
    public Optional<Member> findByMemberEmail(String email);

    // 이메일로 회원 ID 찾기
    @Query("SELECT m.id FROM Member m WHERE m.memberEmail = :email")
    public Long findIdByMemberEmail(@Param("email") String email);

    // 이름과 이메일로 회원 찾기
    public Optional<Member> findByMemberNameAndMemberEmail(String memberName, String memberEmail);

    // 전화번호로 아이디 찾기
    Optional<Member> findByMemberPhone(String memberPhone);

    @Query("SELECT m.memberEmail FROM Member m WHERE m.memberPhone = :phone")
    Optional<String> findEmailByMemberPhone(@Param("phone") String phone);


    // 이름으로 이메일 목록 찾기
    @Query("SELECT m.memberEmail FROM Member m WHERE m.memberName = :name")
    public List<String> findEmailsByMemberName(@Param("name") String name);

    // 회원가입 시 이메일 중복 체크
    public boolean existsByMemberEmail(String email);

    // 회원가입
    public Member save(Member member);

    // 회원 탈퇴
    public void deleteById(Long id);

    // 회원 삭제
    public void delete(Member member);


}
