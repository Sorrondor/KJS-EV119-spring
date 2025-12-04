package com.app.ev119.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "response")
public class TraumaCenterLIstInfoResponse {
//    외상센터 목록정보 조회
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

        // item 태그가 없기 때문에 빈 리스트 또는 null로 들어올 수 있게 설정
        @JacksonXmlElementWrapper(useWrapping = false)
        @JsonProperty("item")
        private List<Object> itemList;
    }
}
