package org.nightdivers.kupica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_EMAIL;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_NICKNAME_EMPTY_STRING;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_1_EMAIL;
import static org.nightdivers.kupica.support.factory.MemberFactory.createTestMember1;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.nightdivers.kupica.domain.member.Member;
import org.nightdivers.kupica.domain.member.MemberRepository;
import org.nightdivers.kupica.service.dto.MemberDto;

class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Spy
    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    /* TARGET append 메소드 테스트 */
    @DisplayName("정상적인 데이터가 들어왔을 경우 추가 성공")
    @Test
    void givenValidMemberDto_whenAppend_thenSuccess() {
        // given
        when(memberRepository.save(any(Member.class))).thenReturn(createTestMember1());

        // when
        MemberDto actual = memberService.append(MemberDto.fromEntity(createTestMember1()));

        // then
        verify(memberRepository).save(any(Member.class));
        assertEquals(MemberDto.fromEntity(createTestMember1()), actual);
    }

    /* TARGET isExist 메소드 테스트 */
    @DisplayName("이메일이 존재하는 경우 true 반환")
    @Test
    void givenExistEmail_whenIsExist_thenTrue() {
        // given
        when(memberRepository.existsByEmailAddressAndErasedFlagIsFalse(TEST_INVALID_MEMBER_EMAIL)).thenReturn(true);

        // when
        boolean actual = memberService.isExist(TEST_INVALID_MEMBER_EMAIL);

        // then
        verify(memberRepository).existsByEmailAddressAndErasedFlagIsFalse(TEST_INVALID_MEMBER_EMAIL);
        assertTrue(actual);
    }

    @DisplayName("이메일이 존재하지 않는 경우 false 반환")
    @Test
    void givenNotExistEmail_whenIsExist_thenFalse() {
        // given
        when(memberRepository.existsByEmailAddressAndErasedFlagIsFalse(TEST_INVALID_MEMBER_EMAIL)).thenReturn(false);

        // when
        boolean actual = memberService.isExist(TEST_INVALID_MEMBER_EMAIL);

        // then
        verify(memberRepository).existsByEmailAddressAndErasedFlagIsFalse(TEST_INVALID_MEMBER_EMAIL);
        assertFalse(actual);
    }

    /* TARGET getMemberByEmail 메소드 테스트 */
    @DisplayName("이메일이 존재하는 경우 MemberDto 반환")
    @Test
    void givenExistEmail_whenGetMemberByEmail_thenSuccess() {
        // given
        Member member = createTestMember1();
        when(memberRepository.findByEmailAddressAndErasedFlagIsFalse(TEST_MEMBER_1_EMAIL)).thenReturn(Optional.of(member));

        // when
        MemberDto actual = memberService.getMemberByEmail(TEST_MEMBER_1_EMAIL);

        // then
        verify(memberRepository).findByEmailAddressAndErasedFlagIsFalse(TEST_MEMBER_1_EMAIL);
        assertEquals(MemberDto.fromEntity(member), actual);
    }

    @DisplayName("이메일이 존재하지 않는 경우 NoSuchElementException 발생")
    @Test
    void givenNotExistEmail_whenGetMemberByEmail_thenThrowNoSuchElementException() {
        // given
        when(memberRepository.findByEmailAddressAndErasedFlagIsFalse(TEST_INVALID_MEMBER_EMAIL)).thenReturn(Optional.empty());

        // when, then
        assertThrows(NoSuchElementException.class, () -> memberService.getMemberByEmail(TEST_INVALID_MEMBER_EMAIL));
    }

    /* TARGET updateNickname 메소드 테스트 */
    @DisplayName("정상적인 nickname 이 들어왔을 경우 nickname 업데이트 성공")
    @Test
    void givenValidNickname_whenUpdateNickname_thenSuccess() {
        // given
        Member prevMember = createTestMember1();
        Member updatedMember = createTestMember1();
        String newNickname = "newNickname";
        updatedMember.changeNickname(newNickname);
        when(memberRepository.findByEmailAddressAndErasedFlagIsFalse(TEST_MEMBER_1_EMAIL)).thenReturn(Optional.of(prevMember));
        when(memberRepository.save(any(Member.class))).thenReturn((updatedMember));

        // when
        MemberDto actual = memberService.updateNickname(newNickname, prevMember.getEmailAddress());

        // then
        verify(memberRepository).save(any(Member.class));
        assertEquals(newNickname, actual.nickname());
    }

    @DisplayName("유효하지 않은 nickname 이 들어왔을 경우 IllegalArgumentException 발생")
    @Test
    void givenInvalidNickname_whenUpdateNickname_thenThrowIllegalArgumentException() {
        // given
        when(memberRepository.findByEmailAddressAndErasedFlagIsFalse(TEST_MEMBER_1_EMAIL)).thenReturn(Optional.of(createTestMember1()));

        // when, then
        assertThrows(
                IllegalArgumentException.class,
                () -> memberService.updateNickname(TEST_INVALID_NICKNAME_EMPTY_STRING, createTestMember1().getEmailAddress())
        );
    }

    /* TARGET remove 메소드 테스트 */
    @DisplayName("정상적인 데이터가 들어왔을 경우 삭제 성공")
    @Test
    void givenValidMemberDto_whenRemove_thenSuccess() {
        // given
        Member member = createTestMember1();
        doReturn(MemberDto.fromEntity(member)).when(memberService).getMemberByEmail(member.getEmailAddress());
        when(memberRepository.save(member)).thenReturn(member);

        // when
        memberService.remove(member.getEmailAddress());

        // then
        verify(memberRepository).save(any(Member.class));
    }

    @DisplayName("이메일이 존재하지 않는 경우 NoSuchElementException 발생")
    @Test
    void givenNotExistEmail_whenRemove_thenThrowNoSuchElementException() {
        // given
        doThrow(new NoSuchElementException()).when(memberService).getMemberByEmail(TEST_INVALID_MEMBER_EMAIL);

        // when, then
        assertThrows(NoSuchElementException.class, () -> memberService.remove(TEST_INVALID_MEMBER_EMAIL));
    }
}
