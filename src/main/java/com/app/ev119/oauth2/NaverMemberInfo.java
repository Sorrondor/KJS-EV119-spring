package com.app.ev119.oauth2;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class NaverMemberInfo implements OAuth2MemberInfo {

    private Map<String, Object > attributes;

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("sub");
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
        return (String) attributes.get("mobile");
    }
 }
