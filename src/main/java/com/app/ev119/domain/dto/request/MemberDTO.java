package com.app.ev119.domain.dto.request;

import com.app.ev119.domain.dto.AddressDTO;
import com.app.ev119.domain.dto.HealthDTO;
import com.app.ev119.domain.dto.MemberStaffDTO;
import com.app.ev119.domain.entity.*;
import com.app.ev119.domain.type.BloodAbo;
import com.app.ev119.domain.type.BloodRh;
import com.app.ev119.domain.type.GenderType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;

@Getter @Setter
@ToString @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor @NoArgsConstructor
public class MemberDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String memberEmail;
//    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private Health health;

    private List<Medication> medications;
    private List<Allergy> allergies;
    private List<EmergencyPhone> emergencyPhones;
    private List<Address> addresses;
    private List<MemberSocial> memberSocials;
    private List<MemberStaff> memberStaffs;
    private List<Visited> visited;
}
