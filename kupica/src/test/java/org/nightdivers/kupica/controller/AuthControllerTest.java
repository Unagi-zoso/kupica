package org.nightdivers.kupica.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.nightdivers.kupica.support.factory.MemberFactory.createTestMember1;
import static org.nightdivers.kupica.support.provider.TestAuthenticationApplier.applyAnonymousAuth;
import static org.nightdivers.kupica.support.provider.TestAuthenticationApplier.applyMemberAuth;
import static org.nightdivers.kupica.support.provider.TestAuthenticationApplier.applySigningUpAuth;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.nightdivers.kupica.controller.dto.RegisterRequest;
import org.nightdivers.kupica.service.AuthService;
import org.nightdivers.kupica.service.dto.CustomOAuth2User;
import org.nightdivers.kupica.service.dto.MemberDto;
import org.nightdivers.kupica.support.annotation.ControllerTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@DisplayNameGeneration(ReplaceUnderscores.class)
@RequiredArgsConstructor
@WebMvcTest(AuthController.class)
@ControllerTest
public class AuthControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    private final AuthService authService;

    @Nested
    class 회원가입_POST_요청_시 {

        @Nested
        class 회원_권한인_경우 {

            String requestURI;

            @BeforeEach
            void context() {
                requestURI = "/api/v1/auth/register";
                applyMemberAuth();
            }

            @Test
            void 실패한다() throws Exception {
                mockMvc.perform(post(requestURI).with(csrf()))
                        .andExpect(status().isForbidden());
            }
        }

        @Nested
        class 회원가입_중_권한인_경우 {

            String requestURI;

            @BeforeEach
            void context() {
                requestURI = "/api/v1/auth/register";
                applySigningUpAuth();
                MemberDto expectedMemberDto = MemberDto.fromEntity(createTestMember1());

                when(authService.signup(any(RegisterRequest.class), any(CustomOAuth2User.class))).thenReturn(expectedMemberDto);
            }

        }

        @Nested
        class 익명_권한인_경우 {

            String requestURI;

            @BeforeEach
            void context() {
                requestURI = "/api/v1/auth/register";
                applyAnonymousAuth();
            }

            @Test
            void 실패한다() throws Exception {
                mockMvc.perform(post(requestURI).with(csrf()))
                        .andExpect(status().isForbidden());
            }
        }
    }
}
