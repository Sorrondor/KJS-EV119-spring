package com.app.ev119.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@ToString(exclude = {
        "member"
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "TBL_EMERGENCY_PHONE")
@SequenceGenerator(
        name = "SEQ_EMERGENCY_PHONE_GENERATOR",
        sequenceName = "SEQ_EMERGENCY_PHONE",
        allocationSize = 1
)
public class EmergencyPhone {
    @Id @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_EMERGENCY_PHONE_GENERATOR")
    private Long id;

    private String emergencyPhoneName;
    private String emergencyPhoneRelationship;
    private String emergencyPhoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
