package com.app.ev119.domain.entity;

import com.app.ev119.domain.type.VisitedType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter @Setter
@ToString(exclude = {
        "member"
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "TBL_VISITED")
@SequenceGenerator(
        name = "SEQ_VISITED_GENERATOR",
        sequenceName = "SEQ_VISITED",
        allocationSize = 1
)
public class Visited {

    @Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_VISITED_GENERATOR")
    @EqualsAndHashCode.Include
    private Long id;

    private Date visitedDate;
    private String visitedName;
    private String visitedDepartment;

    @Enumerated(EnumType.STRING)
    private VisitedType visitedType;

    private String visitedReason;
    private String visitedDiagnosis;
    private String visitedTreatmentContent;
    private String visitedDoctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
