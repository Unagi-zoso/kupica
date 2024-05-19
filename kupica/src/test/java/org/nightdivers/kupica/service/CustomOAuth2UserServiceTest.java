package org.nightdivers.kupica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.nightdivers.kupica.domain.member.UserRole.MEMBER;
import static org.nightdivers.kupica.domain.member.UserRole.SIGNING_UP;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_EMAIL;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_1_SOCIAL_LOGIN_TYPE;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_VALID_MEMBER_EMAIL;
import static org.nightdivers.kupica.support.factory.MemberFactory.createTestMember1;
import static org.nightdivers.kupica.support.factory.OAuth2UserFactory.createTestMemberAttributes;
import static org.nightdivers.kupica.support.factory.OAuth2UserFactory.createTestNotMemberAttributes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.nightdivers.kupica.service.dto.CustomOAuth2User;
import org.nightdivers.kupica.service.dto.KakaoResponse;
import org.nightdivers.kupica.service.dto.MemberDto;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

@DisplayNameGeneration(ReplaceUnderscores.class)
class CustomOAuth2UserServiceTest {

    @Mock
    MemberService memberService;
    @Mock
    OAuth2User oAuth2User;
    @Mock
    OAuth2UserRequest userRequest;
    @Mock
    ClientRegistration clientRegistration;
    @Mock
    DefaultOAuth2UserService defaultOAuth2UserService;
    @InjectMocks
    CustomOAuth2UserService customOAuth2UserService;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Nested
    class 소셜_로그인_이용자_정보_획득_시 {

        @Nested
        class 이미_회원가입한_이용자일_경우 {

            OAuth2User expected;

            @BeforeEach
            void context() {
                when(defaultOAuth2UserService.loadUser(userRequest)).thenReturn(oAuth2User);
                when(oAuth2User.getAttributes()).thenReturn(createTestMemberAttributes());
                when(userRequest.getClientRegistration()).thenReturn(clientRegistration);
                when(userRequest.getClientRegistration().getRegistrationId()).thenReturn(
                        TEST_MEMBER_1_SOCIAL_LOGIN_TYPE.getDescription());
                when(memberService.isExist(TEST_VALID_MEMBER_EMAIL)).thenReturn(true);
                when(memberService.getMemberByEmail(TEST_VALID_MEMBER_EMAIL)).thenReturn(MemberDto.fromEntity(createTestMember1()));

                expected = new CustomOAuth2User(new KakaoResponse(createTestMemberAttributes()), MEMBER);
            }

            @Test
            void 회원_상태의_이용자_정보를_반환한다() {
                OAuth2User actual = customOAuth2UserService.loadUser(userRequest);

                assertEquals(actual.getName(), expected.getName());
                assertEquals(actual.getAuthorities(), expected.getAuthorities());
                verify(defaultOAuth2UserService, times(1)).loadUser(userRequest);
                verify(memberService, times(1)).isExist(TEST_VALID_MEMBER_EMAIL);
                verify(memberService, times(1)).getMemberByEmail(TEST_VALID_MEMBER_EMAIL);
            }
        }

        @Nested
        class 회원가입을_하지_않은_이용자일_경우 {

            CustomOAuth2User expected;

            @BeforeEach
            void context() {
                when(defaultOAuth2UserService.loadUser(userRequest)).thenReturn(oAuth2User);
                when(oAuth2User.getAttributes()).thenReturn(createTestNotMemberAttributes());
                when(userRequest.getClientRegistration()).thenReturn(clientRegistration);
                when(userRequest.getClientRegistration().getRegistrationId()).thenReturn(
                        TEST_MEMBER_1_SOCIAL_LOGIN_TYPE.getDescription());
                when(memberService.isExist(TEST_INVALID_MEMBER_EMAIL)).thenReturn(false);

                expected = new CustomOAuth2User(new KakaoResponse(createTestNotMemberAttributes()), SIGNING_UP);
            }

            @Test
            void 회원가입_중_상태의_이용자_정보를_반환한다() {
                CustomOAuth2User actual = (CustomOAuth2User) customOAuth2UserService.loadUser(userRequest);

                assertEquals(actual.getName(), expected.getName());
                assertEquals(actual.getAttributes(), expected.getAttributes());
                assertEquals(actual.getAuthorities(), expected.getAuthorities());
                verify(memberService, times(1)).isExist(TEST_INVALID_MEMBER_EMAIL);
            }
        }
    }
}
