package com.app.ev119.domain.entity;

import com.app.ev119.domain.type.StaffCertType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter @Setter
@ToString(exclude = {
        "memberStaff"
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "TBL_STAFF_CERT")
@SequenceGenerator(
        name = "SEQ_STAFF_CERT_GENERATOR",
        sequenceName = "SEQ_STAFF_CERT",
        allocationSize = 1
)
public class StaffCert {

    @Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_STAFF_CERT_GENERATOR")
    @EqualsAndHashCode.Include
    private Long id;
    private String staffCertContent;
    private Date staffCertDate;

    @Enumerated(EnumType.STRING)
    private StaffCertType staffCertType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_STAFF_ID")
    private MemberStaff memberStaff;

}
