package com.app.ev119.repository;

import com.app.ev119.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    
    // 회원가입
    public Member save(Member member);
    
    // 이매일로 회원 찾기
    public Optional<Member> findByMemberEmail(String email);

    // 회원가입 시 이메일 중복 체크
    public boolean existsByMemberEmail(String email);

}
