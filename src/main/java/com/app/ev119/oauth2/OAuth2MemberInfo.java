package com.app.ev119.oauth2;

public interface OAuth2MemberInfo {

    String getProvider();
    String getProviderId();
    String getEmail();
    String getName();

    default String getMobNo() {
        return null;
    }

}
