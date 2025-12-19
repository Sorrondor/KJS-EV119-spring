package com.app.ev119.domain.entity;

import com.app.ev119.domain.type.UrgencyType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter @Setter
@ToString(exclude = {
        "firstAidProcedures"
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "TBL_FIRST_AID")
@SequenceGenerator(
        name = "SEQ_FIRST_AID_GENERATOR",
        sequenceName = "SEQ_FIRST_AID",
        allocationSize = 1
)
public class FirstAid {
    @Id @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_FIRST_AID_GENERATOR")
    private Long id;
    private UrgencyType urgency;
    @OneToMany(mappedBy = "firstAid")
    private List<FirstAidProcedures> firstAidProcedures;
    @OneToMany(mappedBy = "firstAid")
    private List<FirstAidKeywords> firstAidKeywords;
}
