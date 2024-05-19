package org.nightdivers.kupica.controller;

import static org.nightdivers.kupica.support.provider.TestAuthenticationApplier.applyAnonymousAuth;
import static org.nightdivers.kupica.support.provider.TestAuthenticationApplier.applyMemberAuth;
import static org.nightdivers.kupica.support.provider.TestAuthenticationApplier.applySigningUpAuth;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.nightdivers.kupica.service.AuthService;
import org.nightdivers.kupica.support.annotation.ControllerTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@DisplayNameGeneration(ReplaceUnderscores.class)
@RequiredArgsConstructor
@WebMvcTest(ViewController.class)
@ControllerTest
public class ViewControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    private final AuthService authService;

    @Nested
    class 메인_루트_페이지_GET_요청_시 {

        @Nested
        class 정상_접근할_경우 {

            @Test
            void 메인_루트_페이지를_반환한다() throws Exception {
                mockMvc.perform(get("/"))
                        .andExpect(status().isOk())
                        .andExpect(view().name("index"));
            }
        }
    }

    @Nested
    class 정적_리소스_GET_요청_시 {

        @Nested
        class 정상_접근할_경우 {

            @MethodSource("org.nightdivers.kupica.support.provider.StaticResourceUriProvider#provideStaticResourceUriParameters")
            @ParameterizedTest
            void 정적_리소스를_반환한다(String requestUri) throws Exception {
                mockMvc.perform(get(requestUri))
                        .andExpect(status().isOk());
            }
        }
    }

    @Nested
    class 로그인_페이지_GET_요청_시 {

        @Nested
        class 회원_권한으로_접근할_경우 {

            @BeforeEach
            void context() {
                applyMemberAuth();
            }

            @Test
            void 로그인_페이지를_반환한다() throws Exception {
                mockMvc.perform(get("/login"))
                        .andExpect(status().isForbidden());
            }
        }

        @Nested
        class 회원가입_중_권한으로_접근할_경우 {

            @BeforeEach
            void context() {
                applySigningUpAuth();
            }

            @Test
            void 로그인_페이지를_반환한다() throws Exception {
                mockMvc.perform(get("/login"))
                        .andExpect(status().isOk())
                        .andExpect(view().name("login"));
            }
        }

        @Nested
        class 익명_권한으로_접근할_경우 {

            @BeforeEach
            void context() {
                applyAnonymousAuth();
            }

            @Test
            void 로그인_페이지를_반환한다() throws Exception {
                mockMvc.perform(get("/login"))
                        .andExpect(status().isOk())
                        .andExpect(view().name("login"));
            }
        }
    }

    @Nested
    class 회원가입_페이지_GET_요청_시 {

        @Nested
        class 회원_권한으로_접근할_경우 {

            @BeforeEach
            void context() {
                applyMemberAuth();
            }

            @Test
            void 회원가입_페이지를_반환한다() throws Exception {
                mockMvc.perform(get("/register"))
                        .andExpect(status().isForbidden());
            }
        }

        @Nested
        class 회원가입_중_권한으로_접근할_경우 {

            @BeforeEach
            void context() {
                applySigningUpAuth();
            }

            @Test
            void 회원가입_페이지를_반환한다() throws Exception {
                mockMvc.perform(get("/register"))
                        .andExpect(status().isOk())
                        .andExpect(view().name("register"));
            }
        }

        @Nested
        class 익명_권한으로_접근할_경우 {

            @BeforeEach
            void context() {
                applyAnonymousAuth();
            }

            @Test
            void 회원가입_페이지를_반환한다() throws Exception {
                mockMvc.perform(get("/register"))
                        .andExpect(status().isForbidden());
            }
        }
    }
}
