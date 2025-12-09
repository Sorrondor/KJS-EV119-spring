package com.app.ev119.domain.entity;

import com.app.ev119.domain.type.AllergyType;
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
class AllergyTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void createAllergyTable() {
        Allergy allergy = new Allergy();
        allergy.setAllergyName("알러지 명");
        allergy.setAllergyType(AllergyType.MEDICINE);

        Member foundMember = entityManager.find(Member.class, 1L);
        allergy.setMember(foundMember);

        entityManager.persist(allergy);

        log.info("Allergy created successfully: {}", allergy);
    }

    @Test
    public void readAllergyTable() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QMember member = QMember.member;
        QAllergy allergy = QAllergy.allergy;

        List<Tuple> memberAndAllergy = queryFactory
                .select(member, allergy)
                .from(member)
                .join(allergy)
                .on(member.id.eq(allergy.member.id))
                .fetch();

        log.info("memberAndAllergy: {}", memberAndAllergy);
    }

    @Test
    public void updateAllergyTable() {
        Allergy foundAllergy = entityManager.find(Allergy.class, 1L);
        entityManager.detach(foundAllergy);

        foundAllergy.setAllergyName("수정된 알러지 명");
        foundAllergy.setAllergyType(AllergyType.OTHER);

        entityManager.merge(foundAllergy);

        log.info("updatedAllergy: {}", foundAllergy);
    }

    @Test
    public void deleteAllergyTable() {
        Allergy allergy = entityManager.find(Allergy.class, 1L);
        entityManager.remove(allergy);
        Allergy foundAllergy = entityManager.find(Allergy.class, 1L);
        log.info("deletedAllergy: {}", foundAllergy);
    }

}