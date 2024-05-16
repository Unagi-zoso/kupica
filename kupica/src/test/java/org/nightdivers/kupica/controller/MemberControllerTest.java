package org.nightdivers.kupica.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.nightdivers.kupica.support.provider.TestAuthenticationApplier.applyMemberAuth;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.nightdivers.kupica.domain.member.UserRole;
import org.nightdivers.kupica.service.WithdrawService;
import org.nightdivers.kupica.support.annotation.ControllerTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@RequiredArgsConstructor
@WebMvcTest(MemberController.class)
@ControllerTest
public class MemberControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    private final WithdrawService withdrawService;

    /* TARGET : 회원탈퇴 요청 테스트 */
    @Test
    void MEMBER_권환으로_회원탈퇴_요청_성공() throws Exception {
        // given
        String requestURI = "/api/v1/member/withdraw";
        applyMemberAuth();

        doNothing().when(withdrawService).withdrawUser(any(String.class), any(UserRole.class));

        // when & then
        mockMvc.perform(delete(requestURI).with(csrf()))
                .andExpect(status().isOk());
    }
}
