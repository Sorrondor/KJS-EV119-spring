package com.app.ev119.domain.dto;

import com.app.ev119.domain.entity.Member;
import com.app.ev119.domain.type.AddressType;
import lombok.*;

import java.util.Date;

@Getter @Setter
@ToString @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor @NoArgsConstructor
public class AddressDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String addressStreet;
    private String addressRoad;
    private String addressZipcode;
    private String addressLatitude;
    private String addressLongitude;
    private Date addressCreateAt;
    private AddressType addressType;
    private Long memberId;
}
