package com.app.ev119.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "response")
public class EmergencyRoomMessageResponse {
//    응급실 및 중증질환 메시지 조회
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
        // <items/> 형태 → 내부에 item 없음 → null 또는 빈 구조로 들어옴
    }
}
