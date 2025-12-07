package com.app.ev119.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffCertDTO {

    private Long id;
    private String staffCertContent;
    private Date staffCertDate;

}
