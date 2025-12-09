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
        "member",
        "diseases"
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "TBL_HEALTH")
@SequenceGenerator(
        name = "SEQ_HEALTH_GENERATOR",
        sequenceName = "SEQ_HEALTH",
        allocationSize = 1
)
public class Health {
    @Id @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_HEALTH_GENERATOR")
    private Long id;

    @Enumerated(EnumType.STRING)
    private BloodRh healthBloodRh;
    @Enumerated(EnumType.STRING)
    private BloodAbo healthBloodAbo;

    private Double healthHeight;
    private Double healthWeight;

    @Enumerated(EnumType.STRING)
    private GenderType healthGender;

    @OneToOne(cascade = CascadeType.ALL)
    private Member member;

    @OneToMany(mappedBy = "health")
    private List<Disease> diseases;

    {
        diseases =  new ArrayList<>();
    }
}
