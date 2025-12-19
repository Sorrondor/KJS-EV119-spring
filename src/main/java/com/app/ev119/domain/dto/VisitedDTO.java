package com.app.ev119.domain.dto;

import com.app.ev119.domain.type.VisitedType;
import lombok.*;

import java.util.Date;

@Getter @Setter
@ToString @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor @NoArgsConstructor
public class VisitedDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Date visitedDate;
    private String visitedName;
    private String visitedDepartment;
    private VisitedType visitedType;
    private String visitedReason;
    private String visitedDiagnosis;
    private String visitedTreatmentContent;
    private String visitedDoctor;
    private Long memberId;
}
