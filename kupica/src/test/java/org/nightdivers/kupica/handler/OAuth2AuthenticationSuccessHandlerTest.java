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
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.nightdivers.kupica.service.MemberService;
import org.springframework.security.core.Authentication;

@DisplayNameGeneration(ReplaceUnderscores.class)
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

    @Nested
    class 소셜_로그인_성공_시 {

        @Nested
        class 이미_회원가입한_이용자일_경우 {

            @BeforeEach
            void context() {
                when(authentication.getPrincipal()).thenReturn(createTestOAuth2User(MEMBER));
                when(memberService.isExist(TEST_VALID_MEMBER_EMAIL)).thenReturn(true);
            }

            @Test
            void 루트_페이지로_리다이렉션한다() throws Exception {
                successHandler.onAuthenticationSuccess(request, response, authentication);

                verify(memberService, times(1)).isExist(TEST_VALID_MEMBER_EMAIL);
                verify(response).sendRedirect(request.getContextPath() + "/");
            }
        }

        @Nested
        class 회원가입을_하지_않은_이용자일_경우 {

            @BeforeEach
            void context() {
                when(authentication.getPrincipal()).thenReturn(createTestOAuth2User(SIGNING_UP));
                when(memberService.isExist(TEST_VALID_MEMBER_EMAIL)).thenReturn(false);
            }

            @Test
            void 회원가입_페이지로_리다이렉션한다() throws Exception {
                successHandler.onAuthenticationSuccess(request, response, authentication);

                verify(memberService, times(1)).isExist(TEST_VALID_MEMBER_EMAIL);
                verify(response).sendRedirect(request.getContextPath() + "/register");
            }
        }
    }
}

