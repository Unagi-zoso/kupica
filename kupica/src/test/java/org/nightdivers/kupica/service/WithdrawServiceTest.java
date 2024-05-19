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
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@DisplayNameGeneration(ReplaceUnderscores.class)
public class WithdrawServiceTest {

    @Mock
    MemberService memberService;
    @InjectMocks
    WithdrawService withdrawService;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Nested
    class 회원_탈퇴_시 {

        @Nested
        class 유효한_이메일이_들어온_경우 {

            @BeforeEach
            void context() {
                doNothing().when(memberService).remove(TEST_MEMBER_1_EMAIL);
            }

            @Test
            void 해당_회원을_제거한다() {
                withdrawService.withdrawUser(TEST_MEMBER_1_EMAIL);

                verify(memberService).remove(TEST_MEMBER_1_EMAIL);
            }
        }

        @Nested
        class 유효하지_않은_이메일이_들어온_경우 {

            @BeforeEach
            void context() {
                doThrow(NoSuchElementException.class).when(memberService).remove(TEST_INVALID_MEMBER_EMAIL);
            }

            @Test
            void NoSuchElementException_예외를_던진다() {
                assertThrows(NoSuchElementException.class, () -> withdrawService.withdrawUser(TEST_INVALID_MEMBER_EMAIL));
                verify(memberService).remove(TEST_INVALID_MEMBER_EMAIL);
            }
        }
    }
}
