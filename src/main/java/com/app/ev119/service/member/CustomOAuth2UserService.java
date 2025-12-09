package com.app.ev119.service.member;

import com.app.ev119.domain.entity.Member;
import com.app.ev119.oauth2.OAuth2MemberInfo;
import com.app.ev119.oauth2.OAuth2MemberInfoFactory;
import com.app.ev119.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String resisterId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        Map<String,Object> attributes = oAuth2User.getAttributes();

        OAuth2MemberInfo oAuth2MemberInfo = OAuth2MemberInfoFactory.of(resisterId, attributes);

        Member member = saveOrUpdate(oAuth2MemberInfo);

        Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_MEMBER"));

        String nameAttributeKey = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        return new DefaultOAuth2User(authorities, attributes, nameAttributeKey);
    }

    private Member saveOrUpdate(OAuth2MemberInfo oAuth2MemberInfo) {
        String email = oAuth2MemberInfo.getEmail();

        if(email == null){
            log.warn("소셜 로그인 정보가 없습니다. provider:{}, providerId:{}", oAuth2MemberInfo.getProvider(), oAuth2MemberInfo.getProviderId());

        }

        Member member = memberRepository.findByMemberEmail(email).orElseGet(() -> createSocialMember(oAuth2MemberInfo));

        return memberRepository.save(member);

    }

    private Member createSocialMember(OAuth2MemberInfo memberInfo) {

        Member member = Member.builder()
                .memberEmail(memberInfo.getEmail())
                .memberPassword(null)
                .memberName(memberInfo.getName())
                .memberGender(null)
                .memberBloodRh(null)
                .memberBloodAbo(null)
                .build();

        return memberRepository.save(member);
    }

}
