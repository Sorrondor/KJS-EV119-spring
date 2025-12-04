package com.app.ev119.domain.entity;

import com.app.ev119.domain.type.BloodAbo;
import com.app.ev119.domain.type.BloodRh;
import com.app.ev119.domain.type.GenderType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@ToString(exclude = {
        "addresses",
        "memberHealths",
        "memberStaffs"
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "TBL_MEMBER")
@SequenceGenerator(
        name = "SEQ_MEMBER_GENERATOR",
        sequenceName = "SEQ_MEMBER",
        allocationSize = 1
)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_MEMBER_GENERATOR")
    @EqualsAndHashCode.Include
    private Long id;
    private String MemberEmail;
    private String MemberPassword;
    private String MemberName;

    @Enumerated(EnumType.STRING)
    private GenderType MemberGender;

    @Enumerated(EnumType.STRING)
    private BloodRh MemberBloodRh;

    @Enumerated(EnumType.STRING)
    private BloodAbo MemberBloodAbo;

    @OneToMany(mappedBy = "member")
    private List<MemberStaff> memberStaffs;
    @OneToMany(mappedBy = "member")
    private List<Address> addresses;
    @OneToMany(mappedBy = "member")
    private List<MemberHealth> memberHealths;

    {
        addresses = new ArrayList<>();
        memberHealths = new ArrayList<>();
        memberStaffs = new ArrayList<>();
    }
}
