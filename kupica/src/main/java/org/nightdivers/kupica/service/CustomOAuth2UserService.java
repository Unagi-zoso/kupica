package org.nightdivers.kupica.service;

import static org.nightdivers.kupica.domain.member.SocialLoginType.KAKAO;
import static org.nightdivers.kupica.domain.member.UserRole.MEMBER;
import static org.nightdivers.kupica.domain.member.UserRole.SIGNING_UP;

import lombok.RequiredArgsConstructor;
import org.nightdivers.kupica.service.dto.CustomOAuth2User;
import org.nightdivers.kupica.service.dto.KakaoResponse;
import org.nightdivers.kupica.service.dto.OAuth2Response;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberService memberService;

    private final DefaultOAuth2UserService defaultOAuth2UserService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = defaultOAuth2UserService.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId().toUpperCase();

        final OAuth2Response oAuth2Response;
        if (registrationId.equals(KAKAO.getDescription())) {
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        } else {
            throw new IllegalArgumentException("지원하지 않는 소셜 로그인입니다.");
        }

        if (memberService.isExist(oAuth2Response.getEmail())) {
            String nickname = memberService.getMemberByEmail(oAuth2Response.getEmail()).nickname();
            return new CustomOAuth2User(oAuth2Response, MEMBER, nickname);
        } else {
            return new CustomOAuth2User(oAuth2Response, SIGNING_UP);
        }
    }
}
