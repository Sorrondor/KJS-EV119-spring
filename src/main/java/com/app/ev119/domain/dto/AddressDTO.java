package com.app.ev119.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {

    private Long id;
    private String addressStreet;
    private String addressRoad;
    private String addressZipcode;
    private String addressLatitude;
    private String addressLongitude;
    private Date addressCreateAt;
}
