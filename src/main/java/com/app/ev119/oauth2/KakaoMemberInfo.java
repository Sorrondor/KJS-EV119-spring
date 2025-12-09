package com.app.ev119.oauth2;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class KakaoMemberInfo implements OAuth2MemberInfo{

    private Map<String, Object> attributes;

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        Object id = attributes.get("id");
        return id != null ? String.valueOf(id) : null;
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getMobNo() {
        return "";
    }

}
