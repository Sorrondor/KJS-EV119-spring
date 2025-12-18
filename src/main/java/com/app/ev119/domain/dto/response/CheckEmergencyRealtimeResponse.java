package com.app.ev119.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "response")
public class CheckEmergencyRealtimeResponse {
//    응급실 실시간 가용병상정보 조회

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
        @JacksonXmlElementWrapper(localName = "items")
        @JacksonXmlProperty(localName = "item")
        private List<CheckEmergencyRealtimeItem> items;

        @JsonProperty("numOfRows")
        private int numOfRows;

        @JsonProperty("pageNo")
        private int pageNo;

        @JsonProperty("totalCount")
        private int totalCount;
    }

//    @Data
//    public static class Items {
//        @JacksonXmlElementWrapper(localName = "items")
//        @JsonProperty("item")
//        private List<CheckEmergencyRealtimeItem> itemList;
//    }
}
