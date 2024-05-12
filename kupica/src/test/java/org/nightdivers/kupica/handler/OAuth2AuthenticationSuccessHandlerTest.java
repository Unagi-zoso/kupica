package org.nightdivers.kupica.handler;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.nightdivers.kupica.domain.member.UserRole.MEMBER;
import static org.nightdivers.kupica.domain.member.UserRole.SIGNING_UP;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_VALID_MEMBER_EMAIL;
import static org.nightdivers.kupica.support.factory.OAuth2UserFactory.createTestOAuth2User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.nightdivers.kupica.service.MemberService;
import org.springframework.security.core.Authentication;

class OAuth2AuthenticationSuccessHandlerTest {

    @Mock
    private MemberService memberService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private OAuth2AuthenticationSuccessHandler successHandler;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    /* TARGET onAuthenticationSuccess 메서드 테스트 */
    @DisplayName("Member가 DB 에 존재하는 경우 루트 페이지 redirection")
    @Test
    public void givenExistMember_whenOnAuthenticationSuccess_thenRedirectToRoot() throws Exception {
        // given
        when(authentication.getPrincipal()).thenReturn(createTestOAuth2User(MEMBER));
        when(memberService.isExist(TEST_VALID_MEMBER_EMAIL)).thenReturn(true);

        // when
        successHandler.onAuthenticationSuccess(request, response, authentication);

        // then
        verify(memberService, times(1)).isExist(TEST_VALID_MEMBER_EMAIL);
        verify(response).sendRedirect(request.getContextPath() + "/");
    }

    @DisplayName("Member가 DB 에 존재하지 않는 경우 회원가입 페이지 redirection")
    @Test
    public void givenNotExistMember_whenOnAuthenticationSuccess_thenRedirectToRegister() throws Exception {
        // given
        when(authentication.getPrincipal()).thenReturn(createTestOAuth2User(SIGNING_UP));
        when(memberService.isExist(TEST_VALID_MEMBER_EMAIL)).thenReturn(false);

        // when
        successHandler.onAuthenticationSuccess(request, response, authentication);

        // then
        verify(memberService, times(1)).isExist(TEST_VALID_MEMBER_EMAIL);
        verify(response).sendRedirect(request.getContextPath() + "/register");
    }
}

