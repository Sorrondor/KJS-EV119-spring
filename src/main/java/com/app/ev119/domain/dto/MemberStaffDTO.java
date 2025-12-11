package com.app.ev119.domain.dto;

import com.app.ev119.domain.entity.Member;
import com.app.ev119.domain.entity.StaffCert;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@ToString @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor @NoArgsConstructor
public class MemberStaffDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String memberStaffIndustry;
    private Long memberId;
    private List<StaffCertDTO> staffCerts;
}
