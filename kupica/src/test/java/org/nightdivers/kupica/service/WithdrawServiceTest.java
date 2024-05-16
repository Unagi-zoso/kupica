package org.nightdivers.kupica.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.nightdivers.kupica.domain.member.UserRole.MEMBER;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_EMAIL;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_1_EMAIL;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class WithdrawServiceTest {

    @Mock
    MemberService memberService;

    @InjectMocks
    WithdrawService withdrawService;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    /* TARGET : withdrawUser 메서드 테스트 */
    @Test
    void 유효한_이메일이_들어온_경우_성공() {
        // given
        doNothing().when(memberService).remove(TEST_MEMBER_1_EMAIL);

        // when
        withdrawService.withdrawUser(TEST_MEMBER_1_EMAIL, MEMBER);

        // then
        verify(memberService).remove(TEST_MEMBER_1_EMAIL);
    }

    @Test
    void 유효하지_않은_이메일이_들어온_경우_실패() {
        // given
        doThrow(NoSuchElementException.class).when(memberService).remove(TEST_INVALID_MEMBER_EMAIL);

        // when & then
        assertThrows(NoSuchElementException.class, () -> withdrawService.withdrawUser(TEST_INVALID_MEMBER_EMAIL, MEMBER));
        verify(memberService).remove(TEST_INVALID_MEMBER_EMAIL);
    }
}
