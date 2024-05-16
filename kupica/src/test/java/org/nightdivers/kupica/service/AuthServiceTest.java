package org.nightdivers.kupica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.nightdivers.kupica.domain.member.UserRole.MEMBER;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_NICKNAME;
import static org.nightdivers.kupica.support.factory.MemberFactory.createTestMember1;
import static org.nightdivers.kupica.support.factory.OAuth2UserFactory.createTestOAuth2User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.nightdivers.kupica.controller.dto.RegisterRequest;
import org.nightdivers.kupica.service.dto.MemberDto;

class AuthServiceTest {

    @InjectMocks
    AuthService authService;

    @Mock
    private MemberService memberService;

    @BeforeEach()
    public void setUp() {
        openMocks(this);
    }

    /* TARGET : signup 메서드 테스트 */
    @DisplayName("유효한 데이터가 있는 경우")
    @Test
    void givenValidData_whenSignup_thenSuccess() {
        // given
        when(memberService.append(any(MemberDto.class))).thenReturn(MemberDto.fromEntity(createTestMember1()));

        // when
        authService.signup(new RegisterRequest("TEST"), createTestOAuth2User(MEMBER));

        // then
        verify(memberService).append(any(MemberDto.class));
        assertEquals(
                MemberDto.fromEntity(createTestMember1()), authService.signup(new RegisterRequest("TEST"), createTestOAuth2User(MEMBER))
        );

    }

    @DisplayName("Member 저장에 실패하는 경우")
    @Test
    void givenMemberSaveFailure_whenSignup_thenThrowException() {
        // given
        doThrow(new IllegalArgumentException()).when(memberService).append(any(MemberDto.class));

        // when & then
        assertThrows(
                IllegalArgumentException.class,
                () -> authService.signup(new RegisterRequest(TEST_INVALID_MEMBER_NICKNAME), createTestOAuth2User(MEMBER))
        );
    }
}
