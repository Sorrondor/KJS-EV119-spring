package com.app.ev119.domain.dto;

import com.app.ev119.domain.entity.Member;
import lombok.*;

@Getter @Setter
@ToString @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor @NoArgsConstructor
public class MedicationDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String medicationName;
    private String medicationUsage;
    private String medicationTakingtime;
    private Long memberId;
}
