package com.app.ev119.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmergencyMedicalItem {

    @JsonProperty("dutyAddr")
    private String dutyAddr;

    @JsonProperty("dutyEmcls")
    private String dutyEmcls;

    @JsonProperty("dutyEmclsName")
    private String dutyEmclsName;

    @JsonProperty("dutyName")
    private String dutyName;

    @JsonProperty("dutyTel1")
    private String dutyTel1;

    @JsonProperty("dutyTel3")
    private String dutyTel3;

    @JsonProperty("hpid")
    private String hpid;

    @JsonProperty("phpid")
    private String phpid;

    @JsonProperty("rnum")
    private Integer rnum;

    @JsonProperty("wgs84Lat")
    private Double wgs84Lat;

    @JsonProperty("wgs84Lon")
    private Double wgs84Lon;
}
