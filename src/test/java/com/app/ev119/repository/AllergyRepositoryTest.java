package com.app.ev119.repository;

import com.app.ev119.domain.entity.Allergy;
import com.app.ev119.domain.entity.Member;
import com.app.ev119.domain.type.AllergyType;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest @Slf4j
@Transactional @Commit
class AllergyRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AllergyRepository allergyRepository;

    @Test
    void saveAllergy() {
        Member member = memberRepository.findById(1L).orElseThrow();
        Allergy allergy = new Allergy();
        allergy.setAllergyName("Allergy");
        allergy.setAllergyType(AllergyType.OTHER);
        allergy.setMember(member);
        allergyRepository.saveAllergy(allergy);
    }

    @Test
    void saveAllergies() {
        Member member = memberRepository.findById(1L).orElseThrow();
        List<Allergy> allergies = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Allergy allergy = new Allergy();
            allergy.setAllergyName("Allergy"+i);
            allergy.setAllergyType(AllergyType.OTHER);
            allergy.setMember(member);
            allergies.add(allergy);
        }
        allergyRepository.saveAllergies(allergies);
    }

    @Test
    void findByMember_Id() {
        Member member = memberRepository.findById(1L).orElseThrow();
        Long memberId = member.getId();
        List<Allergy> allergyList = allergyRepository.findByMember_Id(memberId);
        log.info("allergyList:{}", allergyList);
    }

    @Test
    void findAllAllergies() {
        List<Allergy> allergyList = allergyRepository.findAll();
        log.info("allergyList:{}", allergyList);
    }

    @Test
    void deleteByMember_Id() {
        Member member = memberRepository.findById(1L).orElseThrow();
        Long memberId = member.getId();
        allergyRepository.deleteByMember_Id(memberId);
    }
}