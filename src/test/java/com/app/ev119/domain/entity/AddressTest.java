package com.app.ev119.domain.entity;

import com.app.ev119.domain.type.AddressType;
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
@Commit @Transactional
class AddressTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void memberInsertTest() {
        Member member = new Member();
        member.setMemberEmail("test123@gmail.com");
        member.setMemberName("홍길동");
        member.setMemberPassword("test123");
        member.setMemberPhone("01012341234");

        entityManager.persist(member);
    }

    @Test
    public void createAddressTable(){
        Address address = new Address();
        address.setAddressRoad("도로 이름");
        address.setAddressStreet("지번");
        address.setAddressZipcode("12312");
        address.setAddressLatitude("위도");
        address.setAddressLongitude("경도");
//        address.setAddressCreateAt();
        address.setAddressType(AddressType.OTHER);
        Member member = entityManager.find(Member.class, 1L);
        address.setMember(member);
        entityManager.persist(address);
    }

    @Test
    public void readAddressTable(){
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QMember member = QMember.member;
        QAddress address = QAddress.address;

        List<Tuple> memberAndAddress = queryFactory
                .select(member, address)
                .from(member)
                .leftJoin(address)
                .on(address.member.id.eq(member.id))
                .fetch();

        log.info("memberAndAddress={}", memberAndAddress);
    }

    @Test
    public void updateAddressTable(){
        Address address = entityManager.find(Address.class, 1L);

        entityManager.detach(address);

        address.setAddressRoad("수정된 도로 이름");
        address.setAddressStreet("수정된 지번");
        address.setAddressZipcode("11111");
        address.setAddressLatitude("수정된 위도");
        address.setAddressLongitude("수정된 경도");
        address.setAddressType(AddressType.HOME);

        entityManager.merge(address);

        Address updatedAddress = entityManager.find(Address.class, 1L);

        log.info("updatedAddress={}", updatedAddress);
    }

    @Test
    public void deleteAddressTable(){
        Address address = entityManager.find(Address.class, 1L);
        entityManager.remove(address);

        Address foundAddress = entityManager.find(Address.class, 1L);

        log.info("foundAddress={}", foundAddress);
    }
}