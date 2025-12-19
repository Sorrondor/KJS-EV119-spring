package com.app.ev119.domain.dto.response.firstAid;

import com.app.ev119.domain.dto.request.firstAid.FirstAidRequestDTO;
import com.app.ev119.domain.entity.FirstAid;
import com.app.ev119.domain.entity.FirstAidKeywords;
import com.app.ev119.domain.entity.FirstAidProcedures;
import com.app.ev119.domain.type.UrgencyType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FirstAidResponseDTO {
    private Long id;
    private UrgencyType urgency;
    private List<String> firstAidProcedures;
    private List<String> firstAidKeywords;

    public FirstAidResponseDTO(FirstAid firstAid) {
        this.id = firstAid.getId();
        this.urgency = firstAid.getUrgency();
    }
    public FirstAidResponseDTO(FirstAidRequestDTO firstAidRequestDTO) {
        this.urgency = firstAidRequestDTO.getUrgency();
        this.firstAidProcedures = firstAidRequestDTO.getFirstAidProcedures();
        this.firstAidKeywords = firstAidRequestDTO.getFirstAidKeywords();
    }
}
