package com.app.ev119.domain.dto;

import com.app.ev119.domain.entity.MemberStaff;
import com.app.ev119.domain.type.StaffCertType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter @Setter
@ToString @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor @NoArgsConstructor
public class StaffCertDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String staffCertContent;
    private Date staffCertDate;
    private StaffCertType staffCertType;
    private Long memberStaffId;
}
