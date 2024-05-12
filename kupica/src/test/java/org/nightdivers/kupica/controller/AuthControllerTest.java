package org.nightdivers.kupica.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.nightdivers.kupica.support.factory.MemberFactory.createTestMember1;
import static org.nightdivers.kupica.support.provider.JsonParserMethodProvider.parseMapFromString;
import static org.nightdivers.kupica.support.provider.TestAuthenticationApplier.applyAnonymousAuth;
import static org.nightdivers.kupica.support.provider.TestAuthenticationApplier.applyMemberAuth;
import static org.nightdivers.kupica.support.provider.TestAuthenticationApplier.applySigningUpAuth;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nightdivers.kupica.controller.dto.MemberResponse;
import org.nightdivers.kupica.controller.dto.RegisterRequest;
import org.nightdivers.kupica.service.AuthService;
import org.nightdivers.kupica.service.dto.CustomOAuth2User;
import org.nightdivers.kupica.service.dto.MemberDto;
import org.nightdivers.kupica.support.annotation.ControllerTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@RequiredArgsConstructor
@WebMvcTest(AuthController.class)
@ControllerTest
public class AuthControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    private final AuthService authService;

    @DisplayName("회원가입 요청 MEMBER 권한 - [실패] Post /api/v1/auth/register 403")
    @Test
    void givenRegisterURIAndMemberRole_whenPost_thenForbidden() throws Exception {
        // given
        String requestURI = "/api/v1/auth/register";
        applyMemberAuth();

        // when & then
        mockMvc.perform(post(requestURI).with(csrf()))
                .andExpect(status().isForbidden());
    }

    @DisplayName("회원가입 요청 SIGNING UP 권한 - [성공] Post /api/v1/auth/register 302")
    @Test
    void givenRegisterURIAndSigningUpRole_whenPost_thenFound() throws Exception {
        // given
        String requestURI = "/api/v1/auth/register";
        applySigningUpAuth();
        MemberDto expectedMemberDto = MemberDto.fromEntity(createTestMember1());

        when(authService.signup(any(RegisterRequest.class), any(CustomOAuth2User.class))).thenReturn(expectedMemberDto);

        // when & then
        mockMvc.perform(post(requestURI).with(csrf()))
                .andExpect(status().isFound())
                .andExpect(result -> {
                    MemberResponse response = parseMapFromString(result.getResponse().getContentAsString(), MemberResponse.class);
                    assertAll(
                            () -> assertEquals("/login", result.getResponse().getHeader("Location")),
                            () -> assertEquals(MemberResponse.fromMemberDto(expectedMemberDto).nickname(), response.nickname()),
                            () -> assertEquals(MemberResponse.fromMemberDto(expectedMemberDto).email(), response.email())
                    );
                });
    }

    @DisplayName("회원가입 요청 ANONYMOUS 권한 - [실패] Post /api/v1/auth/register 403")
    @Test
    void givenRegisterURIAndAnonymousRole_whenPost_thenForbidden() throws Exception {
        // given
        String requestURI = "/api/v1/auth/register";
        applyAnonymousAuth();

        // when & then
        mockMvc.perform(post(requestURI).with(csrf()))
                .andExpect(status().isForbidden());
    }
}
