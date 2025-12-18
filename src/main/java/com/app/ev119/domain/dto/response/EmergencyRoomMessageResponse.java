package com.app.ev119.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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

        @JacksonXmlElementWrapper(localName = "items")
        @JacksonXmlProperty(localName = "item")
        @JsonSetter(nulls = Nulls.AS_EMPTY)
        private List<Item> items = new ArrayList<>();

        @JsonProperty("numOfRows")
        private int numOfRows;

        @JsonProperty("pageNo")
        private int pageNo;

        @JsonProperty("totalCount")
        private int totalCount;
    }

    @Data
    public static class Item {
        private String dutyAddr;
        private String dutyName;
        private String emcOrgCod;
        private String hpid;
        private int rnum;

        private String symBlkEndDtm;
        private String symBlkMsg;
        private String symBlkMsgTyp;
        private String symBlkSttDtm;
        private String symOutDspMth;
        private String symOutDspYon;
        private String symTypCod;
        private String symTypCodMag;
    }
}
