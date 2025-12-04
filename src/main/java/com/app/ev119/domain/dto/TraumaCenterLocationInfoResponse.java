package com.app.ev119.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "response")
public class TraumaCenterLocationInfoResponse {
//    외상센터 위치정보 조회

    @JsonProperty("header")
    private Header header;

    @JsonProperty("body")
    private Body body;

    @Data
    public static class Header {
        @JsonProperty("resultCode")
        private String resultCode;

        @JsonProperty("resultMsg")
        private String resultMsg;
    }

    @Data
    public static class Body {

        @JsonProperty("items")
        private Items items;

        @JsonProperty("numOfRows")
        private int numOfRows;

        @JsonProperty("pageNo")
        private int pageNo;

        @JsonProperty("totalCount")
        private int totalCount;
    }

    @Data
    public static class Items {

        @JsonProperty("item")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<Item> itemList;
    }

    @Data
    public static class Item {

        @JsonProperty("cnt")
        private String cnt;

        @JsonProperty("distance")
        private String distance;

        @JsonProperty("dutyAddr")
        private String dutyAddr;

        @JsonProperty("dutyDiv")
        private String dutyDiv;

        @JsonProperty("dutyDivName")
        private String dutyDivName;

        @JsonProperty("dutyName")
        private String dutyName;

        @JsonProperty("dutyTel1")
        private String dutyTel1;

        @JsonProperty("endTime")
        private String endTime;

        @JsonProperty("hpid")
        private String hpid;

        @JsonProperty("latitude")
        private String latitude;

        @JsonProperty("longitude")
        private String longitude;

        @JsonProperty("rnum")
        private String rnum;

        @JsonProperty("startTime")
        private String startTime;
    }
}
