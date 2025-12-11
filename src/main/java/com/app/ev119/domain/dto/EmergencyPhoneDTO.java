package com.app.ev119.domain.dto;

import com.app.ev119.domain.entity.Member;
import lombok.*;

@Getter @Setter
@ToString @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor @NoArgsConstructor
public class EmergencyPhoneDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String emergencyPhoneName;
    private String emergencyPhoneRelationship;
    private String emergencyPhoneNumber;
    private Long memberId;
}
