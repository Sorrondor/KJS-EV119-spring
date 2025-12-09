package com.app.ev119.domain.entity;

import com.app.ev119.domain.type.BloodAbo;
import com.app.ev119.domain.type.BloodRh;
import com.app.ev119.domain.type.GenderType;
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
class HealthTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void createHealthTable() {
        Health health = new Health();
        health.setHealthBloodRh(BloodRh.PLUS);
        health.setHealthBloodAbo(BloodAbo.B);
        health.setHealthGender(GenderType.M);
        health.setHealthHeight(169.5);
        health.setHealthWeight(51.5);
        Member member = entityManager.find(Member.class, 1L);
//        member.setHealth([member.getHealth(), health].toArray());
        health.setMember(member);

        entityManager.persist(health);

        log.info("createHealthTable: {}", health);
    }

    @Test
    public void readHealthTable() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QMember qMember = QMember.member;
        QHealth qHealth = QHealth.health;

        List<Tuple> memberAndHealth = queryFactory
                .select(qMember, qHealth)
                .from(qMember)
                .join(qHealth)
                .on(qMember.id.eq(qHealth.member.id))
                .fetch();

        log.info("memberAndHealth: {}", memberAndHealth);
    }

    @Test
    public void updateHealthTable() {
        Health foundHealth = entityManager.find(Health.class, 1L);
        entityManager.detach(foundHealth);

        foundHealth.setHealthBloodRh(BloodRh.MINUS);
        foundHealth.setHealthBloodAbo(BloodAbo.A);
        foundHealth.setHealthGender(GenderType.W);
        foundHealth.setHealthHeight(159.5);
        foundHealth.setHealthWeight(41.5);

        entityManager.merge(foundHealth);

        log.info("updatedHealth: {}", foundHealth);
    }

    @Test
    public void deleteHealthTable() {
        Health health = entityManager.find(Health.class, 1L);

        entityManager.remove(health);
        Health foundHealth = entityManager.find(Health.class, 1L);
        log.info("deletedHealth: {}", foundHealth);
    }

}