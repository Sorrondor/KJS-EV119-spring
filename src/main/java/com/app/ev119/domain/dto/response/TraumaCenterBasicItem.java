package com.app.ev119.domain.dto.response;

import lombok.Data;

@Data
public class TraumaCenterBasicItem {
    private String dgidIdName;
    private String dutyAddr;
    private String dutyInf;
    private String dutyMapimg;
    private String dutyName;
    private String dutyTel1;
    private String dutyTel3;

    private String hpid;

    private Double wgs84Lat;
    private Double wgs84Lon;


    private Integer hpbdn;
    private Integer hpccuyn;
    private Integer hpcuyn;
    private Integer hperyn;
    private Integer hpgryn;
    private Integer hpicuyn;
    private Integer hpnicuyn;
    private Integer hpopyn;


    private Integer hvcc;
    private Integer hvccc;
    private Integer hvec;
    private Integer hvgc;
    private Integer hvncc;
    private Integer hvoc;


    private String dutyTime1s;
    private String dutyTime1c;
    private String dutyTime2s;
    private String dutyTime2c;
    private String dutyTime3s;
    private String dutyTime3c;
    private String dutyTime4s;
    private String dutyTime4c;
    private String dutyTime5s;
    private String dutyTime5c;
    private String dutyTime6s;
    private String dutyTime6c;
}
