package com.app.ev119.repository;

import com.app.ev119.domain.entity.Visited;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitedRepository extends JpaRepository<Visited, Long> {
//    단일 방문 이력 추가
//    단일 방문 이력 업데이트
    default void saveVisited(Visited visited){
        save(visited);
    }

//    목록 형태의 방문 이력 추가
//    목록 형태의 방문 이력 업데이트
    default void saveVisitedList(List<Visited> allergies){
        saveAll(allergies);
    }

//    멤버의 모든 방문 이력 조회
    public List<Visited> findByMember_Id(Long memberId);

//    모든 방문 이력 목록 조회
    default List<Visited> findAllVisitedList(){
        return findAll();
    }

//    단일 방문 이력 삭제
    public void deleteById(Long id);

//    멤버의 모든 방문 이력 삭제
    public void deleteByMember_Id(Long memberId);
}
