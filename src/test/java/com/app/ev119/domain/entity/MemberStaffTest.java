package com.app.ev119.domain.entity;

import com.app.ev119.domain.type.SocialType;
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
class MemberStaffTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void createMemberStaffTestTable() {
        MemberStaff memberStaff = new MemberStaff();
        memberStaff.setMemberStaffIndustry("증명서");

        Member member = entityManager.find(Member.class, 1L);
        memberStaff.setMember(member);

        entityManager.persist(memberStaff);

        log.info("created memberStaff: {}", memberStaff);
    }

    @Test
    public void readMemberStaffTable() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QMember qMember = QMember.member;
        QMemberStaff qMemberStaff = QMemberStaff.memberStaff;

        List<Tuple> memberAndMemberStaff = queryFactory
                .select(qMember, qMemberStaff)
                .from(qMember)
                .join(qMemberStaff)
                .on(qMember.id.eq(qMemberStaff.member.id))
                .fetch();

        log.info("memberAndMemberStaff: {}", memberAndMemberStaff);
    }

    @Test
    public void updateMemberStaffTable() {
        MemberStaff foundMemberStaff = entityManager.find(MemberStaff.class, 1L);
        entityManager.detach(foundMemberStaff);

        foundMemberStaff.setMemberStaffIndustry("수정된 증명서");

        entityManager.merge(foundMemberStaff);

        log.info("updatedMemberStaff: {}", foundMemberStaff);
    }

    @Test
    public void deleteMemberStaffTable() {
        MemberStaff memberStaff = entityManager.find(MemberStaff.class, 1L);
        entityManager.remove(memberStaff);
        MemberStaff foundMemberStaff = entityManager.find(MemberStaff.class, 1L);
        log.info("deletedMemberStaff: {}", foundMemberStaff);
    }
    
}