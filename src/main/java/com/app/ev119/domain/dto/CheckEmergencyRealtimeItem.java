package com.app.ev119.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CheckEmergencyRealtimeItem {

    @JsonProperty("dutyName")
    private String dutyName;

    @JsonProperty("dutyTel3")
    private String dutyTel3;

    @JsonProperty("hpid")
    private String hpid;


    @JsonProperty("hv10") private String hv10;
    @JsonProperty("hv11") private String hv11;
    @JsonProperty("hv2")  private String hv2;
    @JsonProperty("hv3")  private String hv3;
    @JsonProperty("hv28") private String hv28;
    @JsonProperty("hv29") private String hv29;
    @JsonProperty("hv30") private String hv30;
    @JsonProperty("hv32") private String hv32;
    @JsonProperty("hv34") private String hv34;
    @JsonProperty("hv35") private String hv35;
    @JsonProperty("hv40") private String hv40;
    @JsonProperty("hv41") private String hv41;
    @JsonProperty("hv42") private String hv42;
    @JsonProperty("hv5")  private String hv5;
    @JsonProperty("hv6")  private String hv6;
    @JsonProperty("hv7")  private String hv7;

    @JsonProperty("hvamyn")     private String hvamyn;
    @JsonProperty("hvangioayn") private String hvangioayn;
    @JsonProperty("hvccc")      private String hvccc;
    @JsonProperty("hvcrrtayn")  private String hvcrrtayn;
    @JsonProperty("hvctayn")    private String hvctayn;

    @JsonProperty("hvec")       private String hvec;
    @JsonProperty("hvecmoayn")  private String hvecmoayn;
    @JsonProperty("hvgc")       private String hvgc;
    @JsonProperty("hvhypoayn")  private String hvhypoayn;
    @JsonProperty("hvicc")      private String hvicc;
    @JsonProperty("hvidate")    private String hvidate;
    @JsonProperty("hvincuayn")  private String hvincuayn;
    @JsonProperty("hvmriayn")   private String hvmriayn;
    @JsonProperty("hvncc")      private String hvncc;
    @JsonProperty("hvoc")       private String hvoc;
    @JsonProperty("hvoxyayn")   private String hvoxyayn;

    @JsonProperty("hvventiayn")     private String hvventiayn;
    @JsonProperty("hvventisoayn")   private String hvventisoayn;


    @JsonProperty("hvs01") private String hvs01;
    @JsonProperty("hvs02") private String hvs02;
    @JsonProperty("hvs03") private String hvs03;
    @JsonProperty("hvs04") private String hvs04;
    @JsonProperty("hvs06") private String hvs06;
    @JsonProperty("hvs07") private String hvs07;
    @JsonProperty("hvs08") private String hvs08;
    @JsonProperty("hvs09") private String hvs09;
    @JsonProperty("hvs12") private String hvs12;
    @JsonProperty("hvs15") private String hvs15;
    @JsonProperty("hvs16") private String hvs16;
    @JsonProperty("hvs17") private String hvs17;
    @JsonProperty("hvs18") private String hvs18;
    @JsonProperty("hvs22") private String hvs22;
    @JsonProperty("hvs24") private String hvs24;
    @JsonProperty("hvs25") private String hvs25;
    @JsonProperty("hvs26") private String hvs26;
    @JsonProperty("hvs27") private String hvs27;
    @JsonProperty("hvs28") private String hvs28;
    @JsonProperty("hvs29") private String hvs29;
    @JsonProperty("hvs30") private String hvs30;
    @JsonProperty("hvs31") private String hvs31;
    @JsonProperty("hvs32") private String hvs32;
    @JsonProperty("hvs33") private String hvs33;
    @JsonProperty("hvs34") private String hvs34;
    @JsonProperty("hvs35") private String hvs35;
    @JsonProperty("hvs38") private String hvs38;

    @JsonProperty("phpid")
    private String phpid;

    @JsonProperty("rnum")
    private String rnum;
}
