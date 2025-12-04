package com.app.ev119.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = {
        "member",
        "staffCerts"
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_MEMBER_STAFF")
@SequenceGenerator(
        name = "SEQ_MEMBER_STAFF_GENERATOR",
        sequenceName = "SEQ_MEMBER_STAFF",
        allocationSize = 1
)
public class MemberStaff {

    @Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_MEMBER_STAFF_GENERATOR")
    @EqualsAndHashCode.Include
    private Long id;

    private String memberStaffIndustry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "memberStaff")
    private List<StaffCert> staffCerts;

    {
        staffCerts = new ArrayList<>();
    }
}
