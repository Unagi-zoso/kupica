package org.nightdivers.kupica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_EMAIL;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_VALID_MEMBER_EMAIL;
import static org.nightdivers.kupica.support.factory.MemberFactory.createTestMember1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.nightdivers.kupica.domain.member.Member;
import org.nightdivers.kupica.domain.member.MemberRepository;
import org.nightdivers.kupica.service.dto.MemberDto;

class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    /* TARGET save 메소드 테스트 */
    @DisplayName("정상적인 데이터가 들어왔을 경우 저장 성공")
    @Test
    void givenValidMemberDto_whenSave_thenSuccess() {
        // given
        when(memberRepository.save(any(Member.class))).thenReturn(createTestMember1());

        // when
        MemberDto actual = memberService.save(MemberDto.fromEntity(createTestMember1()));

        // then
        verify(memberRepository).save(any(Member.class));
        assertEquals(MemberDto.fromEntity(createTestMember1()), actual);
    }

    /* TARGET isExist 메소드 테스트 */
    @DisplayName("이메일이 존재하는 경우 true 반환")
    @Test
    void givenExistEmail_whenIsExist_thenTrue() {
        // given
        when(memberRepository.existsByEmailAddress(TEST_VALID_MEMBER_EMAIL)).thenReturn(true);

        // when
        boolean actual = memberService.isExist(TEST_VALID_MEMBER_EMAIL);

        // then
        verify(memberRepository).existsByEmailAddress(TEST_VALID_MEMBER_EMAIL);
        assertTrue(actual);
    }

    @DisplayName("이메일이 존재하지 않는 경우 false 반환")
    @Test
    void givenNotExistEmail_whenIsExist_thenFalse() {
        // given
        when(memberRepository.existsByEmailAddress(TEST_INVALID_MEMBER_EMAIL)).thenReturn(false);

        // when
        boolean actual = memberService.isExist(TEST_INVALID_MEMBER_EMAIL);

        // then
        verify(memberRepository).existsByEmailAddress(TEST_INVALID_MEMBER_EMAIL);
        assertFalse(actual);
    }
}
