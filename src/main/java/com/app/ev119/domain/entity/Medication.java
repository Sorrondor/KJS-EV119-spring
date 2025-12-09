package com.app.ev119.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@ToString(exclude = {
        "member"
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "TBL_MEDICATION")
@SequenceGenerator(
        name = "SEQ_MEDICATION_GENERATOR",
        sequenceName = "SEQ_MEDICATION",
        allocationSize = 1
)
public class Medication {
    @Id @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_MEDICATION_GENERATOR")
    private Long id;

    private String medicationName;
    private String medicationUsage;
    private String medicationTakingtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
