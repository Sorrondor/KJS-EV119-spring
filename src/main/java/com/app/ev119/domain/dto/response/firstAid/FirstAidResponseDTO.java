package com.app.ev119.domain.dto.response.firstAid;

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
    private List<FirstAidProcedures> firstAidProcedures;
    private List<FirstAidKeywords> firstAidKeywords;

    public FirstAidResponseDTO(FirstAidProcedures firstAidProcedures) {
        this.id = firstAidProcedures.getId();
        this.urgency = firstAidProcedures.getFirstAid().getUrgency();
    }
}
