package com.app.ev119.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@ToString(exclude = {
        "member"
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "TBL_MEMBER_HEALTH")
@SequenceGenerator(
        name = "SEQ_MEMBER_HEALTH_GENERATOR",
        sequenceName = "SEQ_MEMBER_HEALTH",
        allocationSize = 1
)
public class MemberHealth {

    @Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_MEMBER_HEALTH_GENERATOR")
    @EqualsAndHashCode.Include
    private Long id;

    private Integer memberHealthCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
