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
class MemberSocialTest {
    @PersistenceContext
    private EntityManager entityManager;
    
    @Test
    public void createMemberSocialTable() {
        MemberSocial memberSocial = new MemberSocial();
        memberSocial.setMemberSocialProviderId("프로바이더 ID");
        memberSocial.setMemberSocialProvider(SocialType.LOCAL);

        Member member = entityManager.find(Member.class, 1L);
//        member.setMemberSocial([member.getMemberSocial(), memberSocial].toArray());
        memberSocial.setMember(member);

        entityManager.persist(memberSocial);

        log.info("createMemberSocialTable: {}", memberSocial);
    }

    @Test
    public void readMemberSocialTable() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QMember qMember = QMember.member;
        QMemberSocial qMemberSocial = QMemberSocial.memberSocial;

        List<Tuple> memberAndMemberSocial = queryFactory
                .select(qMember, qMemberSocial)
                .from(qMember)
                .join(qMemberSocial)
                .on(qMember.id.eq(qMemberSocial.member.id))
                .fetch();

        log.info("memberAndMemberSocial: {}", memberAndMemberSocial);
    }

    @Test
    public void updateMemberSocialTable() {
        MemberSocial foundMemberSocial = entityManager.find(MemberSocial.class, 1L);
        entityManager.detach(foundMemberSocial);

        foundMemberSocial.setMemberSocialProviderId("수정된 프로바이더 ID");
        foundMemberSocial.setMemberSocialProvider(SocialType.GOOGLE);

        entityManager.merge(foundMemberSocial);

        log.info("updatedMemberSocial: {}", foundMemberSocial);
    }

    @Test
    public void deleteMemberSocialTable() {
        MemberSocial memberSocial = entityManager.find(MemberSocial.class, 1L);
        entityManager.remove(memberSocial);
        MemberSocial foundMemberSocial = entityManager.find(MemberSocial.class, 1L);
        log.info("deletedMemberSocial: {}", foundMemberSocial);
    }
}