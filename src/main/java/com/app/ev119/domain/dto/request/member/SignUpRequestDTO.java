package com.app.ev119.domain.dto.request.member;

import com.app.ev119.domain.dto.AddressDTO;
import com.app.ev119.domain.dto.HealthDTO;
import com.app.ev119.domain.dto.MemberStaffDTO;
import com.app.ev119.domain.type.BloodAbo;
import com.app.ev119.domain.type.BloodRh;
import com.app.ev119.domain.type.GenderType;
import lombok.*;

import java.util.List;

@Getter @Setter
@ToString @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor @NoArgsConstructor
@Builder
public class SignUpRequestDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String memberName;
    private String memberEmail;
    private String memberPassword;
    private String memberPhone;
}
