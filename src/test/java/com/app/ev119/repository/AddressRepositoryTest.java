package com.app.ev119.repository;

import com.app.ev119.domain.entity.Address;
import com.app.ev119.domain.entity.Member;
import com.app.ev119.domain.type.AddressType;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest @Slf4j
@Commit @Transactional
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void saveAddress() {
        Member member = memberRepository.findById(1L).orElseThrow();
        Address address = new Address();
        address.setMember(member);
        address.setAddressRoad("Road");
        address.setAddressStreet("Street");
        address.setAddressZipcode("Zipcode");
        address.setAddressType(AddressType.OTHER);
        address.setAddressLatitude("Latitude");
        address.setAddressLongitude("Longitude");

        addressRepository.saveAddress(address);
    }

    @Test
    void saveAddresses() {
        Member member = memberRepository.findById(1L).orElseThrow();
        List<Address> addresses = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            Address address = new Address();
            address.setMember(member);
            address.setAddressRoad("Road"+i);
            address.setAddressStreet("Street"+i);
            address.setAddressZipcode("Zipcode"+i);
            address.setAddressType(AddressType.OTHER);
            address.setAddressLatitude("Latitude"+i);
            address.setAddressLongitude("Longitude"+i);
            addresses.add(address);
        }

        addressRepository.saveAddresses(addresses);
    }

    @Test
    void findByMember_Id() {
        Member member = memberRepository.findById(1L).orElseThrow();
        Long memberId = member.getId();
        List<Address> addresses = addressRepository.findByMember_Id(memberId);
        log.info("addresses:{}", addresses);
    }

    @Test
    void findAllAddresses() {
        List<Address> addresses = addressRepository.findAllAddresses();
        log.info("addresses:{}", addresses);
    }

    @Test
    void deleteByMember_Id() {
        Member member = memberRepository.findById(1L).orElseThrow();
        Long memberId = member.getId();
        addressRepository.deleteByMember_Id(memberId);
    }
}