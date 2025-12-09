package com.app.ev119.domain.entity;

import com.app.ev119.domain.type.StaffCertType;
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
class StaffCertTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void createStaffCertTestTable() {
        StaffCert staffCert = new StaffCert();
        staffCert.setStaffCertContent("증명 내용");
//        staffCert.setStaffCertDate();
        staffCert.setStaffCertType(StaffCertType.OTHER);

        MemberStaff foundMemberStaff = entityManager.find(MemberStaff.class, 1L);
        staffCert.setMemberStaff(foundMemberStaff);

        entityManager.persist(staffCert);

        log.info("created staffCert: {}", staffCert);
    }

    @Test
    public void readStaffCertTable() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QMember qMember = QMember.member;
        QMemberStaff qMemberStaff = QMemberStaff.memberStaff;
        QStaffCert qStaffCert = QStaffCert.staffCert;

        List<Tuple> memberAndStaffCert = queryFactory
                .select(qMember, qMemberStaff, qStaffCert)
                .from(qMember)
                .join(qMemberStaff)
                .on(qMember.id.eq(qMemberStaff.member.id))
                .join(qStaffCert)
                .on(qMemberStaff.id.eq(qStaffCert.memberStaff.id))
                .fetch();

        log.info("memberAndStaffCert: {}", memberAndStaffCert);
    }

    @Test
    public void updateStaffCertTable() {
        StaffCert foundStaffCert = entityManager.find(StaffCert.class, 1L);
        entityManager.detach(foundStaffCert);

        foundStaffCert.setStaffCertType(StaffCertType.LICENSE);
        foundStaffCert.setStaffCertContent("수정된 증명 내용");

        entityManager.merge(foundStaffCert);

        log.info("updatedStaffCert: {}", foundStaffCert);
    }

    @Test
    public void deleteStaffCertTable() {
        StaffCert staffCert = entityManager.find(StaffCert.class, 1L);
        entityManager.remove(staffCert);
        StaffCert foundStaffCert = entityManager.find(StaffCert.class, 1L);
        log.info("deletedStaffCert: {}", foundStaffCert);
    }
}