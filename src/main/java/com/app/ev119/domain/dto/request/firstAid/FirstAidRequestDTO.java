package com.app.ev119.domain.dto.request.firstAid;

import com.app.ev119.domain.type.UrgencyType;
import lombok.*;

import java.util.List;

@ToString
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class FirstAidRequestDTO {
    private Long id;
    private UrgencyType urgency;
    private List<String> firstAidProcedures;
    private List<String> firstAidKeywords;
}
