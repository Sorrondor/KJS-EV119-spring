package com.app.ev119.oauth2;

import java.util.Map;

public class OAuth2MemberInfoFactory {

    private OAuth2MemberInfoFactory() {
    }

    public static OAuth2MemberInfo of(
            String resisterId,
            Map<String, Object> attributes
    ) {

        if("google".equals(resisterId)){
            return new GoogleMemberInfo(attributes);
        }

        if("naver".equals(resisterId)){
            return new NaverMemberInfo(attributes);
        }

        if("kakao".equals(resisterId)){
            return new KakaoMemberInfo(attributes);
        }

        throw new IllegalArgumentException("지원하지 않는 OAuth2 Provier 입니다 : " + resisterId);
    }
}
