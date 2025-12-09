package com.app.ev119.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @Builder
@ToString(exclude = {
        "medications",
        "allergies",
        "emergencyPhones",
        "addresses",
        "memberSocials",
        "memberStaffs",
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "TBL_MEMBER")
@SequenceGenerator(
        name = "SEQ_MEMBER_GENERATOR",
        sequenceName = "SEQ_MEMBER",
        allocationSize = 1
)
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_MEMBER_GENERATOR")
    @EqualsAndHashCode.Include
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;

    @OneToOne(cascade = CascadeType.ALL)
    private Health health;

    @OneToMany(mappedBy = "member")
    private List<Medication> medications;
    @OneToMany(mappedBy = "member")
    private List<Allergy> allergies;
    @OneToMany(mappedBy = "member")
    private List<EmergencyPhone> emergencyPhones;
    @OneToMany(mappedBy = "member")
    private List<Address> addresses;
    @OneToMany(mappedBy = "member")
    private List<MemberSocial> memberSocials;
    @OneToMany(mappedBy = "member")
    private List<MemberStaff> memberStaffs;

    {
        medications =  new ArrayList<>();
        allergies = new ArrayList<>();
        emergencyPhones = new ArrayList<>();
        addresses = new ArrayList<>();
        memberSocials = new ArrayList<>();
        memberStaffs = new ArrayList<>();
    }
}
