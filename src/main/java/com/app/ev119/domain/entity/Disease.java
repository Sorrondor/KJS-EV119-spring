package com.app.ev119.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@ToString(exclude = {
        "health"
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "TBL_DISEASE")
@SequenceGenerator(
        name = "SEQ_DISEASE_GENERATOR",
        sequenceName = "SEQ_DISEASE",
        allocationSize = 1
)
public class Disease {
    @Id @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DISEASE_GENERATOR")
    private Long id;

    private String diseaseName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HEALTH_ID")
    private Health health;
}
