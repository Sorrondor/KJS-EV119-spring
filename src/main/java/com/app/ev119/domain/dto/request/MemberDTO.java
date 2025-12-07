package com.app.ev119.domain.dto.request;

import com.app.ev119.domain.dto.AddressDTO;
import com.app.ev119.domain.dto.MemberHealthDTO;
import com.app.ev119.domain.dto.MemberStaffDTO;
import com.app.ev119.domain.type.BloodAbo;
import com.app.ev119.domain.type.BloodRh;
import com.app.ev119.domain.type.GenderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private GenderType memberGender;
    private BloodRh  memberBloodRh;
    private BloodAbo memberBloodAbo;
    private List<MemberStaffDTO> memberStaffs;
    private List<AddressDTO> memberAddresses;
    private List<MemberHealthDTO> memberHealths;

}
