package com.app.ev119.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SearchEmergencyLocationInfoItem {
    @JsonProperty("cnt")
    private int cnt;

    @JsonProperty("distance")
    private double distance;

    @JsonProperty("dutyAddr")
    private String dutyAddr;

    @JsonProperty("dutyDiv")
    private String dutyDiv;

    @JsonProperty("dutyDivName")
    private String dutyDivName;

    @JsonProperty("dutyFax")
    private String dutyFax;

    @JsonProperty("dutyName")
    private String dutyName;

    @JsonProperty("dutyTel1")
    private String dutyTel1;

    @JsonProperty("endTime")
    private String endTime;

    @JsonProperty("hpid")
    private String hpid;

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    @JsonProperty("rnum")
    private int rnum;

    @JsonProperty("startTime")
    private String startTime;
}
