package org.nightdivers.kupica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.nightdivers.kupica.domain.member.UserRole.MEMBER;
import static org.nightdivers.kupica.domain.member.UserRole.SIGNING_UP;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_EMAIL;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_SOCIAL_LOGIN_TYPE;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_1_SOCIAL_LOGIN_TYPE;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_VALID_MEMBER_EMAIL;
import static org.nightdivers.kupica.support.factory.MemberFactory.createTestMember1;
import static org.nightdivers.kupica.support.factory.OAuth2UserFactory.createTestMemberAttributes;
import static org.nightdivers.kupica.support.factory.OAuth2UserFactory.createTestNotMemberAttributes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    /* TARGET loadUser 메소드 테스트 */
    @DisplayName("유효하지 않는 소셜 로그인 타입인 경우")
    @Test
    void givenInvalidSocialLoginType_whenLoadUser_thenThrowException() {
        // given
        when(defaultOAuth2UserService.loadUser(userRequest)).thenReturn(oAuth2User);
        when(userRequest.getClientRegistration()).thenReturn(clientRegistration);
        when(userRequest.getClientRegistration().getRegistrationId()).thenReturn(TEST_INVALID_SOCIAL_LOGIN_TYPE);

        // when & then
        assertThrows(IllegalArgumentException.class, () -> customOAuth2UserService.loadUser(userRequest));
    }

    @DisplayName("이미 DB에 존재하는 회원인 경우")
    @Test
    void givenExistMember_whenLoadUser_thenReturnMember() {
        // given
        when(defaultOAuth2UserService.loadUser(userRequest)).thenReturn(oAuth2User);
        when(oAuth2User.getAttributes()).thenReturn(createTestMemberAttributes());
        when(userRequest.getClientRegistration()).thenReturn(clientRegistration);
        when(userRequest.getClientRegistration().getRegistrationId()).thenReturn(
                TEST_MEMBER_1_SOCIAL_LOGIN_TYPE.getDescription());
        when(memberService.isExist(TEST_VALID_MEMBER_EMAIL)).thenReturn(true);
        when(memberService.getMemberByEmail(TEST_VALID_MEMBER_EMAIL)).thenReturn(MemberDto.fromEntity(createTestMember1()));

        OAuth2User expected = new CustomOAuth2User(new KakaoResponse(createTestMemberAttributes()), MEMBER);

        // when
        OAuth2User actual = customOAuth2UserService.loadUser(userRequest);

        // then
        assertEquals(actual.getName(), expected.getName());
        assertEquals(actual.getAuthorities(), expected.getAuthorities());
        verify(memberService, times(1)).isExist(TEST_VALID_MEMBER_EMAIL);
    }

    @DisplayName("DB에 존재하지 않는 회원인 경우")
    @Test
    void givenNotExistMember_whenLoadUser_thenReturnSigningUp() {
        // given
        when(defaultOAuth2UserService.loadUser(userRequest)).thenReturn(oAuth2User);
        when(oAuth2User.getAttributes()).thenReturn(createTestNotMemberAttributes());
        when(userRequest.getClientRegistration()).thenReturn(clientRegistration);
        when(userRequest.getClientRegistration().getRegistrationId()).thenReturn(
                TEST_MEMBER_1_SOCIAL_LOGIN_TYPE.getDescription());
        when(memberService.isExist(TEST_INVALID_MEMBER_EMAIL)).thenReturn(false);

        CustomOAuth2User expected = new CustomOAuth2User(new KakaoResponse(createTestNotMemberAttributes()), SIGNING_UP);

        // when
        CustomOAuth2User actual = (CustomOAuth2User) customOAuth2UserService.loadUser(userRequest);

        // then
        assertEquals(actual.getName(), expected.getName());
        assertEquals(actual.getAttributes(), expected.getAttributes());
        assertEquals(actual.getAuthorities(), expected.getAuthorities());
        verify(memberService, times(1)).isExist(TEST_INVALID_MEMBER_EMAIL);
    }
}
