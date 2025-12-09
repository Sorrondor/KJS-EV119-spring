package com.app.ev119.domain.entity;

import com.app.ev119.domain.type.AllergyType;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@ToString(exclude = {
        "member"
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "TBL_ALLERGY")
@SequenceGenerator(
        name = "SEQ_ALLERGY_GENERATOR",
        sequenceName = "SEQ_ALLERGY",
        allocationSize = 1
)
public class Allergy {
    @Id @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ALLERGY_GENERATOR")
    private Long id;

    @Enumerated(EnumType.STRING)
    private AllergyType allergyType;
    private String allergyName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
