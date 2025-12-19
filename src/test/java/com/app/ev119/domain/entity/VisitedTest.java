package com.app.ev119.domain.entity;

import com.app.ev119.domain.type.VisitedType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest @Slf4j
@Transactional @Commit
class VisitedTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void createVisitedTestTable() {
        Visited visited = new Visited();
//        visited.setVisitedDate();
        visited.setVisitedName("병원명");
        visited.setVisitedDepartment("진료과");
        visited.setVisitedType(VisitedType.CLINIC);
        visited.setVisitedReason("방문 사유");
        visited.setVisitedDiagnosis("진단명");
        visited.setVisitedTreatmentContent("치료 내용");
        visited.setVisitedDoctor("담당 의사");

        Member member = entityManager.find(Member.class, 1L);

        visited.setMember(member);

        entityManager.persist(visited);
    }

}