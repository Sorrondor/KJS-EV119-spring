package com.app.ev119.domain.entity;

import com.app.ev119.domain.type.AddressType;
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
class MemberTest {
    @PersistenceContext
    private EntityManager entityManager;
    @Test
    void insertNormalMember(){
        Member member1 = new Member();

        member1.setMemberName("홍길동");
        member1.setMemberEmail("test123@gmail.com");
        member1.setMemberPassword("1234");
        member1.setMemberGender(GenderType.M);
        member1.setMemberBloodRh(BloodRh.PLUS);
        member1.setMemberBloodAbo(BloodAbo.B);
        entityManager.persist(member1);

        Member member = entityManager.find(Member.class, member1.getId());

        Address address = new Address();
        address.setAddressType(AddressType.HOME);
        address.setAddressStreet("지번주소");
        address.setAddressRoad("도로명 주소");
        address.setAddressZipcode("123");
        address.setMember(member);
        entityManager.persist(address);

        MemberHealth memberHealth = new MemberHealth();
        memberHealth.setMemberHealthCode(1231);
        memberHealth.setMember(member);
        entityManager.persist(memberHealth);

        MemberStaff memberStaff = new MemberStaff();
        memberStaff.setMemberStaffIndustry("면허");
        memberStaff.setMember(member);
        entityManager.persist(memberStaff);

        StaffCert staffCert = new StaffCert();
        staffCert.setStaffCertContent("문자열");
//        staffCert.setStaffCertDate(new Date());
        staffCert.setMemberStaff(memberStaff);
        entityManager.persist(staffCert);

//        address.setMember(member);
//        memberHealth.setMember(member);
//        memberStaff.setMember(member);
//        staffCert.setMemberStaff(memberStaff);
//
//        memberStaff.getStaffCerts().add(staffCert);
//
//        member.getMemberStaffs().add(memberStaff);
//        member.getMemberHealths().add(memberHealth);
    }

    @Test
    public void getMember(){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        QMember qMember = QMember.member;
        QMemberHealth qMemberHealth = QMemberHealth.memberHealth;
        QMemberStaff qMemberStaff = QMemberStaff.memberStaff;
        QAddress qAddress = QAddress.address;
        QStaffCert qStaffCert = QStaffCert.staffCert;

        List<Tuple> fetch = jpaQueryFactory
                .select(qMember, qAddress, qMemberHealth, qMemberStaff, qStaffCert)
                .from(qMember)
                .leftJoin(qAddress)
                .on(qMember.id.eq(qAddress.member.id))
                .leftJoin(qMemberHealth)
                .on(qMember.id.eq(qMemberHealth.member.id))
                .leftJoin(qMemberStaff)
                .on(qMember.id.eq(qMemberStaff.member.id))
                .leftJoin(qStaffCert)
                .on(qMemberStaff.id.eq(qStaffCert.memberStaff.id))
                .fetch();

        fetch.forEach((data)->log.info("{}", data));
    }
}