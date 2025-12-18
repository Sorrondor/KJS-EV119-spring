package com.app.ev119.domain.dto.response;

import lombok.Data;

@Data
public class EmergencyBasicItem {

    private String hpid;
    private String dutyName;
    private String dutyAddr;
    private String dutyTel1;
    private String dutyTel3;

    private String wgs84Lat;
    private String wgs84Lon;

    private String dutyEryn;
    private String dutyHano;
}
