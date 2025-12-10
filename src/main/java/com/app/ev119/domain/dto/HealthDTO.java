package com.app.ev119.domain.dto;

import com.app.ev119.domain.entity.Disease;
import com.app.ev119.domain.entity.Member;
import com.app.ev119.domain.type.BloodAbo;
import com.app.ev119.domain.type.BloodRh;
import com.app.ev119.domain.type.GenderType;
import lombok.*;

import java.util.List;

@Getter @Setter
@ToString @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor @NoArgsConstructor
public class HealthDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private BloodRh healthBloodRh;
    private BloodAbo healthBloodAbo;
    private Double healthHeight;
    private Double healthWeight;
    private GenderType healthGender;
    private Member member;
    private List<Disease> diseases;
}
