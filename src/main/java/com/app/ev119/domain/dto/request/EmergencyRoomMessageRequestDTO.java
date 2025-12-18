package com.app.ev119.domain.dto.request;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class EmergencyRoomMessageRequestDTO {
//  응급실 및 중증질환 메시지 조회

    private Integer pageNo; // 페이지 수
    private Integer numOfRows; // 목록 건수
    private String hpid; // 기관 ID
    private String qn; // 기관명
    private String q0; // 시 도
    private String q1; // 시 군 구
}
