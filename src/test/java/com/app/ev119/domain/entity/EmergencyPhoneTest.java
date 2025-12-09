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
class EmergencyPhoneTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void createEmergencyPhoneTable() {
        EmergencyPhone emergencyPhone = new EmergencyPhone();
        emergencyPhone.setEmergencyPhoneName("지인 이름");
        emergencyPhone.setEmergencyPhoneNumber("01011112222");
        emergencyPhone.setEmergencyPhoneRelationship("부모님");
        Member member = entityManager.find(Member.class, 1L);
//        member.setEmergencyPhone([member.getEmergencyPhone(), emergencyPhone].toArray());
        emergencyPhone.setMember(member);

        entityManager.persist(emergencyPhone);

        log.info("createEmergencyPhoneTable: {}", emergencyPhone);
    }

    @Test
    public void readEmergencyPhoneTable() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QMember qMember = QMember.member;
        QEmergencyPhone qEmergencyPhone = QEmergencyPhone.emergencyPhone;

        List<Tuple> memberAndEmergencyPhone = queryFactory
                .select(qMember, qEmergencyPhone)
                .from(qMember)
                .join(qEmergencyPhone)
                .on(qMember.id.eq(qEmergencyPhone.member.id))
                .fetch();

        log.info("memberAndEmergencyPhone: {}", memberAndEmergencyPhone);
    }

    @Test
    public void updateEmergencyPhoneTable() {
        EmergencyPhone foundEmergencyPhone = entityManager.find(EmergencyPhone.class, 1L);
        entityManager.detach(foundEmergencyPhone);

        foundEmergencyPhone.setEmergencyPhoneName("수정된 이름");
        foundEmergencyPhone.setEmergencyPhoneNumber("01000000000");
        foundEmergencyPhone.setEmergencyPhoneRelationship("누굴까");

        entityManager.merge(foundEmergencyPhone);

        log.info("updatedEmergencyPhone: {}", foundEmergencyPhone);
    }

    @Test
    public void deleteEmergencyPhoneTable() {
        EmergencyPhone emergencyPhone = entityManager.find(EmergencyPhone.class, 1L);
        entityManager.remove(emergencyPhone);
        EmergencyPhone foundEmergencyPhone = entityManager.find(EmergencyPhone.class, 1L);
        log.info("deletedEmergencyPhone: {}", foundEmergencyPhone);
    }

}