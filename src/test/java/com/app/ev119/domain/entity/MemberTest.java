package com.app.ev119.domain.entity;

import com.app.ev119.domain.type.*;
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
class MemberTest {
    @PersistenceContext
    private EntityManager entityManager;
    @Test
    void insertAllMemberData(){
        Member member1 = new Member();

        member1.setMemberName("김길동");
        member1.setMemberEmail("test1234@gmail.com");
        member1.setMemberPassword("1234");
        member1.setMemberPhone("01013572468");
        entityManager.persist(member1);

        Member member = entityManager.find(Member.class, member1.getId());

        Address address = new Address();
        address.setAddressType(AddressType.HOME);
        address.setAddressStreet("지번주소");
        address.setAddressRoad("도로명 주소");
        address.setAddressZipcode("12321");
        address.setAddressLatitude("위도");
        address.setAddressLongitude("경도");
        address.setMember(member);
        entityManager.persist(address);

        Health health = new Health();
        health.setHealthBloodRh(BloodRh.PLUS);
        health.setHealthBloodAbo(BloodAbo.B);
        health.setHealthHeight(169.5);
        health.setHealthWeight(51.5);
        health.setHealthGender(GenderType.M);
        health.setMember(member);
        entityManager.persist(health);

        Disease disease = new Disease();
        disease.setDiseaseName("질병 이름");
        disease.setHealth(health);
        entityManager.persist(disease);

        Medication medication = new Medication();
        medication.setMedicationName("약물 명");
        medication.setMedicationUsage("용법");
        medication.setMedicationTakingtime("복용시간");
        medication.setMember(member);
        entityManager.persist(medication);

        Allergy allergy = new Allergy();
        allergy.setAllergyType(AllergyType.MEDICINE);
        allergy.setAllergyName("알레르기 명");
        allergy.setMember(member);
        entityManager.persist(allergy);

        EmergencyPhone emergencyPhone = new EmergencyPhone();
        emergencyPhone.setEmergencyPhoneName("이름");
        emergencyPhone.setEmergencyPhoneRelationship("관계");
        emergencyPhone.setEmergencyPhoneNumber("01001230123");
        emergencyPhone.setMember(member);
        entityManager.persist(emergencyPhone);

        MemberSocial memberSocial = new MemberSocial();
        memberSocial.setMemberSocialProviderId("프로바이터 ID");
        memberSocial.setMemberSocialProvider(SocialType.LOCAL);
        memberSocial.setMember(member);
        entityManager.persist(memberSocial);

        MemberStaff memberStaff = new MemberStaff();
        memberStaff.setMemberStaffIndustry("면허");
        memberStaff.setMember(member);
        entityManager.persist(memberStaff);

        StaffCert staffCert = new StaffCert();
        staffCert.setStaffCertType(StaffCertType.LICENSE);
        staffCert.setStaffCertContent("인증 내용");
//        staffCert.setStaffCertDate(new Date());
        staffCert.setMemberStaff(memberStaff);
        entityManager.persist(staffCert);

        log.info("inserted all member data: {}", member);
    }

    @Test
    public void getAllMemberData(){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        QMember qMember = QMember.member;
        QMemberSocial qMemberSocial = QMemberSocial.memberSocial;
        QMemberStaff qMemberStaff = QMemberStaff.memberStaff;
        QStaffCert qStaffCert = QStaffCert.staffCert;
        QHealth qHealth = QHealth.health;
        QDisease qDisease = QDisease.disease;
        QMedication qMedication = QMedication.medication;
        QAllergy qAllergy = QAllergy.allergy;
        QEmergencyPhone qEmergencyPhone = QEmergencyPhone.emergencyPhone;
        QAddress qAddress = QAddress.address;

        List<Tuple> memberAllDatas = jpaQueryFactory.select(qMember, qMemberSocial, qMemberStaff, qStaffCert, qHealth, qDisease, qMedication, qAllergy, qEmergencyPhone, qAddress)
                .from(qMember)
                .join(qMemberSocial)
                .on(qMember.id.eq(qMemberSocial.member.id))
                .join(qMemberStaff)
                .on(qMember.id.eq(qMemberStaff.member.id))
                .join(qStaffCert)
                .on(qMemberStaff.id.eq(qStaffCert.memberStaff.id))
                .join(qHealth)
                .on(qMember.id.eq(qHealth.member.id))
                .join(qDisease)
                .on(qHealth.id.eq(qDisease.health.id))
                .join(qMedication)
                .on(qMember.id.eq(qMedication.member.id))
                .join(qAllergy)
                .on(qMember.id.eq(qAllergy.member.id))
                .join(qEmergencyPhone)
                .on(qMember.id.eq(qEmergencyPhone.member.id))
                .join(qAddress)
                .on(qMember.id.eq(qAddress.member.id))
                .fetch();

        memberAllDatas.forEach((data)->log.info("{}", data));
    }

    @Test
    public void insertMemberSocial(){
        Member member1 = new Member();
        member1.setMemberEmail("test123123@gmail.com");
        entityManager.persist(member1);
        MemberSocial memberSocial = new MemberSocial();
        memberSocial.setMemberSocialProviderId("소셜 아이디가 입력됨");
        memberSocial.setMemberSocialProvider(SocialType.LOCAL);
        memberSocial.setMember(member1);
        entityManager.persist(memberSocial);
    }

    @Test
    public void getMemberSocial(){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QMember qMember = QMember.member;
        QMemberSocial qMemberSocial = QMemberSocial.memberSocial;
        List<Tuple> tuples = jpaQueryFactory
                .select(qMember, qMemberSocial)
                .from(qMember)
                .join(qMemberSocial)
                .on(qMember.id.eq(qMemberSocial.member.id))
                .fetch();
        tuples.forEach((tuple)->log.info("{}", tuple));
    }
}