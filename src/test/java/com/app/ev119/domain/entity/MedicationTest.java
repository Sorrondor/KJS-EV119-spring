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
class MedicationTest {
    @PersistenceContext
    private EntityManager entityManager;
    
    @Test
    public void createMedicationTable() {
        Medication medication = new Medication();
        medication.setMedicationName("약물 이름");
        medication.setMedicationTakingtime("투여시간");
        medication.setMedicationUsage("투여 용량");

        Member member = entityManager.find(Member.class, 1L);
//        member.setMedication([member.getMedication(), medication].toArray());
        medication.setMember(member);

        entityManager.persist(medication);

        log.info("createMedicationTable: {}", medication);
    }

    @Test
    public void readMedicationTable() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QMember qMember = QMember.member;
        QMedication qMedication = QMedication.medication;

        List<Tuple> memberAndMedication = queryFactory
                .select(qMember, qMedication)
                .from(qMember)
                .join(qMedication)
                .on(qMember.id.eq(qMedication.member.id))
                .fetch();

        log.info("memberAndMedication: {}", memberAndMedication);
    }

    @Test
    public void updateMedicationTable() {
        Medication foundMedication = entityManager.find(Medication.class, 1L);
        entityManager.detach(foundMedication);

        foundMedication.setMedicationName("수정된 약물 이름");
        foundMedication.setMedicationTakingtime("수정된 투여 시간");
        foundMedication.setMedicationUsage("수정된 투여 용량");

        entityManager.merge(foundMedication);

        log.info("updatedMedication: {}", foundMedication);
    }

    @Test
    public void deleteMedicationTable() {
        Medication medication = entityManager.find(Medication.class, 1L);
        entityManager.remove(medication);
        Medication foundMedication = entityManager.find(Medication.class, 1L);
        log.info("deletedMedication: {}", foundMedication);
    }
    
}