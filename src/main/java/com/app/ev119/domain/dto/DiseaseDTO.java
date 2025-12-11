package com.app.ev119.domain.dto;

import com.app.ev119.domain.entity.Health;
import lombok.*;

@Getter @Setter
@ToString @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor @NoArgsConstructor
public class DiseaseDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String diseaseName;
    private Long healthId;
}
