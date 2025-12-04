package com.app.ev119.domain.entity;

import com.app.ev119.domain.type.AddressType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter @Setter
@ToString(exclude = {
        "member"
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "TBL_ADDRESS")
@SequenceGenerator(
        name = "SEQ_ADDRESS_GENERATOR",
        sequenceName = "SEQ_ADDRESS",
        allocationSize = 1
)
public class Address {

    @Id @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ADDRESS_GENERATOR")
    private Long id;
    private String addressStreet;
    private String addressRoad;
    private String addressZipcode;
    private String addressLatitude;
    private String addressLongitude;
    private Date addressCreateAt;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
