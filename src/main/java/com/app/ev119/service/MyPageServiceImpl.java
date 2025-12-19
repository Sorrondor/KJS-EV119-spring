package com.app.ev119.service;

import com.app.ev119.domain.dto.*;
import com.app.ev119.domain.dto.request.MemberDTO;
import com.app.ev119.domain.entity.*;
import com.app.ev119.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MyPageServiceImpl implements MyPageService {
    private final MemberSocialRepository memberSocialRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;

    private final AddressRepository addressRepository;
    private final AllergyRepository allergyRepository;
    private final DiseaseRepository diseaseRepository;
    private final EmergencyPhoneRepository emergencyPhoneRepository;
    private final HealthRepository healthRepository;
    private final MedicationRepository medicationRepository;
    private final MemberStaffRepository memberStaffRepository;
    private final StaffCertRepository staffCertRepository;
    private final VisitedRepository visitedRepository;

    @Override
    public MemberDTO getMember(Long memberId) {
        Member foundMember = entityManager.find(Member.class, memberId);
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(foundMember.getId());
        memberDTO.setMemberName(foundMember.getMemberName());
        memberDTO.setMemberPhone(foundMember.getMemberPhone());
        memberDTO.setMemberEmail(foundMember.getMemberEmail());
//        memberDTO.setAllergies(foundMember.getAllergies());
//        memberDTO.setHealth(foundMember.getHealth());
//        memberDTO.setMedications(foundMember.getMedications());
//        memberDTO.setVisited(foundMember.getVisited());
//        memberDTO.setEmergencyPhones(foundMember.getEmergencyPhones());
//        memberDTO.setAddresses(foundMember.getAddresses());
//        memberDTO.setMemberSocials(foundMember.getMemberSocials());
//        memberDTO.setMemberStaffs(foundMember.getMemberStaffs());
        return memberDTO;
    }

    @Override
    public void modifyMember(Long memberId, MemberDTO member) {
        Member foundMember = entityManager.find(Member.class, memberId);
        foundMember.setMemberName(member.getMemberName());
        foundMember.setMemberPhone(member.getMemberPhone());
    }

    @Override
    public void removeMember(Long memberId) {
        Member foundMember = entityManager.find(Member.class, memberId);
        addressRepository.deleteByMember_Id(memberId);
        foundMember.setAddresses(null);
        allergyRepository.deleteByMember_Id(memberId);
        foundMember.setAllergies(null);
        diseaseRepository.deleteByHealth_Id(healthRepository.findByMember_Id(memberId).getId());

        emergencyPhoneRepository.deleteByMember_Id(memberId);
        healthRepository.deleteByMember_Id(memberId);
        medicationRepository.deleteByMember_Id(memberId);
        memberStaffRepository.findByMember_Id(memberId).forEach(memberStaff -> {
            staffCertRepository.deleteByMemberStaff_Id(memberStaff.getId());
        });
        memberStaffRepository.deleteByMember_Id(memberId);
        visitedRepository.deleteByMember_Id(memberId);
        memberSocialRepository.deleteByMemberId(memberId);
        entityManager.flush();
        entityManager.remove(foundMember);
    }

    @Override
    public void modifyPassword(Long memberId, String password) {
        Member foundMember = entityManager.find(Member.class, memberId);
        password = passwordEncoder.encode(password);
        foundMember.setMemberPassword(password);
    }

    @Override
    public Long findIdByToken(Authentication tokenDTO) {
        Object principal = tokenDTO.getPrincipal();
        return (Long) principal;
    }
}
