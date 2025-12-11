package com.app.ev119.domain.dto;

import com.app.ev119.domain.entity.Member;
import com.app.ev119.domain.type.AllergyType;
import lombok.*;

@Getter @Setter
@ToString @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor @NoArgsConstructor
public class AllergyDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private AllergyType allergyType;
    private String allergyName;
    private Long memberId;
}
