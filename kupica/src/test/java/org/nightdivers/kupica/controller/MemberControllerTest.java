package org.nightdivers.kupica.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.nightdivers.kupica.support.provider.TestAuthenticationApplier.applyAnonymousAuth;
import static org.nightdivers.kupica.support.provider.TestAuthenticationApplier.applyMemberAuth;
import static org.nightdivers.kupica.support.provider.TestAuthenticationApplier.applySigningUpAuth;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.nightdivers.kupica.service.WithdrawService;
import org.nightdivers.kupica.support.annotation.ControllerTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@DisplayNameGeneration(ReplaceUnderscores.class)
@RequiredArgsConstructor
@WebMvcTest(MemberController.class)
@ControllerTest
public class MemberControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    private final WithdrawService withdrawService;

    @Nested
    class 회원_탈퇴_요청_시 {

        @Nested
        class 회원_권한인_경우 {

            String requestURI;

            @BeforeEach
            void context() {
                requestURI = "/api/v1/member/withdraw";
                applyMemberAuth();
                doNothing().when(withdrawService).withdrawUser(any(String.class));
            }

            @Test
            void 성공한다() throws Exception {
                mockMvc.perform(delete(requestURI).with(csrf()))
                        .andExpect(status().isOk());
            }
        }

        @Nested
        class 회원가입_중_권한인_경우 {

            String requestURI;

            @BeforeEach
            void context() {
                requestURI = "/api/v1/member/withdraw";
                applySigningUpAuth();
            }

            @Test
            void 실패한다() throws Exception {
                mockMvc.perform(delete(requestURI).with(csrf()))
                        .andExpect(status().isForbidden())
                        .andExpect(content().json("{\"code\":\"" + 703 + "\",\"message\":\"" + "권한이 없는 이용자입니다." + "\"}"));
            }
        }

        @Nested
        class 익명_권한인_경우 {

            String requestURI;

            @BeforeEach
            void context() {
                requestURI = "/api/v1/member/withdraw";
                applyAnonymousAuth();
            }

            @Test
            void 실패한다() throws Exception {
                mockMvc.perform(delete(requestURI).with(csrf()))
                        .andExpect(status().isForbidden())
                        .andExpect(content().json("{\"code\":\"" + 703 + "\",\"message\":\"" + "권한이 없는 이용자입니다." + "\"}"));
            }
        }
    }
}
