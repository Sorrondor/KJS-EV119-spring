package com.app.ev119.domain.entity;

import com.app.ev119.domain.type.UrgencyType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter @Setter
@ToString(exclude = {
        "firstAid"
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "TBL_FIRST_AID_KEYWORDS")
@SequenceGenerator(
        name = "SEQ_FIRST_AID_KEYWORDS_GENERATOR",
        sequenceName = "SEQ_FIRST_AID_KEYWORDS",
        allocationSize = 1
)
public class FirstAidKeywords {
    @Id @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_FIRST_AID_KEYWORDS_GENERATOR")
    private Long id;
    private String keyword;
    @ManyToOne
    @JoinColumn(name = "FIRST_AID_ID")
    private FirstAid firstAid;

}
