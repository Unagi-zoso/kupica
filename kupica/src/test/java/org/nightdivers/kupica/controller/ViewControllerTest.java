package org.nightdivers.kupica.controller;

import static org.nightdivers.kupica.support.provider.TestAuthenticationApplier.applyAnonymousAuth;
import static org.nightdivers.kupica.support.provider.TestAuthenticationApplier.applyMemberAuth;
import static org.nightdivers.kupica.support.provider.TestAuthenticationApplier.applySigningUpAuth;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.nightdivers.kupica.service.AuthService;
import org.nightdivers.kupica.support.annotation.ControllerTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@RequiredArgsConstructor
@WebMvcTest(ViewController.class)
@ControllerTest
public class ViewControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    private final AuthService authService;

    @DisplayName("메인 루트 페이지 정상 호출 테스트 - [성공] GET / 200")
    @Test
    void givenRootURI_whenGet_thenSuccess() throws Exception {
        // given
        String requestURI = "/";

        // when & then
        mockMvc.perform(get(requestURI))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @DisplayName("정적 리소스 호출 테스트 -[성공] GET /static/** 200")
    @MethodSource("org.nightdivers.kupica.support.provider.StaticResourceUriProvider#provideStaticResourceUriParameters")
    @ParameterizedTest
    void givenStaticResourceUri_whenGet_thenSuccess(String requestUri) throws Exception {
        // given

        // when & then
        mockMvc.perform(get(requestUri))
                .andExpect(status().isOk());
    }

    @DisplayName("로그인 페이지 호출 테스트 MEMBER 권한 - [실패] GET /login 403")
    @Test
    void givenLoginURIAndMemberRole_whenGet_thenForbidden() throws Exception {
        // given
        String requestURI = "/login";
        applyMemberAuth();

        // when & then
        mockMvc.perform(get(requestURI))
                .andExpect(status().isForbidden());
    }

    @DisplayName("로그인 페이지 호출 테스트 SIGNING_UP 권한 - [성공] GET /login 200")
    @Test
    void givenLoginURIAndSigningUp_whenGet_thenOk() throws Exception {
        // given
        String requestURI = "/login";
        applySigningUpAuth();

        // when & then
        mockMvc.perform(get(requestURI))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @DisplayName("로그인 페이지 호출 테스트 ANONYMOUS 권한 - [성공] GET /login 200")
    @Test
    void givenLoginURIAndAnonymous_whenGet_thenOk() throws Exception {
        // given
        String requestURI = "/login";
        applyAnonymousAuth();

        // when & then
        mockMvc.perform(get(requestURI))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @DisplayName("회원가입 페이지 호출 테스트 MEMBER 권한- [실패] GET /register 403")
    @Test
    void givenRegisterURIAndMemberRole_whenGet_thenForbidden() throws Exception {
        // given
        String requestURI = "/register";
        applyMemberAuth();

        // when & then
        mockMvc.perform(get(requestURI))
                .andExpect(status().isForbidden());
    }

    @DisplayName("회원가입 페이지 호출 테스트 SIGNING_UP 권한 - [성공] GET /register 200")
    @Test
    void givenRegisterURIAndSigningUp_whenGet_thenOk() throws Exception {
        // given
        String requestURI = "/register";
        applySigningUpAuth();

        // when & then
        mockMvc.perform(get(requestURI))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @DisplayName("회원가입 페이지 호출 테스트 ANONYMOUS 권한 - [실패] GET /register 403")
    @Test
    void givenRegisterURIAndAnonymous_whenGet_thenForbidden() throws Exception {
        // given
        String requestURI = "/register";
        applyAnonymousAuth();

        // when & then
        mockMvc.perform(get(requestURI))
                .andExpect(status().isForbidden());
    }
}
