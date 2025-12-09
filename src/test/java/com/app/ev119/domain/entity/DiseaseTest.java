package com.app.ev119.domain.entity;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest @Slf4j
@Transactional @Commit
class DiseaseTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void createDiseaseTable() {
        Disease disease = new Disease();
        disease.setDiseaseName("질병 이름");

        Health health = entityManager.find(Health.class, 1L);
        disease.setHealth(health);

        entityManager.persist(disease);

        log.info("createDiseaseTable: {}", disease);
    }

    @Test
    public void readDiseaseTable() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QMember qMember = QMember.member;
        QHealth qHealth = QHealth.health;
        QDisease qDisease = QDisease.disease;

        List<Tuple> memberAndHealthAndDisease = queryFactory
                .select(qMember, qHealth, qDisease)
                .from(qMember)
                .join(qHealth)
                .on(qMember.id.eq(qHealth.member.id))
                .join(qDisease)
                .on(qHealth.id.eq(qDisease.health.id))
                .fetch();

        log.info("memberAndHealthAndDisease: {}", memberAndHealthAndDisease);
    }

    @Test
    public void updateDiseaseTable() {
        Disease foundDisease = entityManager.find(Disease.class, 1L);
        entityManager.detach(foundDisease);

        foundDisease.setDiseaseName("수정된 질병 명");

        entityManager.merge(foundDisease);

        log.info("updatedDisease: {}", foundDisease);
    }

    @Test
    public void deleteDiseaseTable() {
        Disease disease = entityManager.find(Disease.class, 1L);
        entityManager.remove(disease);
        Disease foundDisease = entityManager.find(Disease.class, 1L);
        log.info("deletedDisease: {}", foundDisease);
    }

}