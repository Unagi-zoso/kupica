package org.nightdivers.kupica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_EMAIL;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_NICKNAME_EMPTY_STRING;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_MEMBER_1_EMAIL;
import static org.nightdivers.kupica.support.factory.MemberFactory.createMockTestMember1;
import static org.nightdivers.kupica.support.factory.MemberFactory.createTestMember1;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.nightdivers.kupica.domain.member.Member;
import org.nightdivers.kupica.domain.member.MemberRepository;
import org.nightdivers.kupica.service.dto.MemberDto;

@DisplayNameGeneration(ReplaceUnderscores.class)
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

    @Nested
    class 회원_가입_시 {

        @Nested
        class 유효한_회원정보가_들어온_경우 {

            @BeforeEach
            void context() {
                when(memberRepository.save(any(Member.class))).thenReturn(createTestMember1());
            }

            @Test
            void 해당_회원을_추가한다() {
                MemberDto actual = memberService.append(MemberDto.fromEntity(createTestMember1()));

                verify(memberRepository).save(any(Member.class));
                assertEquals(MemberDto.fromEntity(createTestMember1()), actual);
            }
        }
    }

    @Nested
    class 이메일로_회원_조회_시 {

        @Nested
        class 유효한_이메일이_들어온_경우 {

            Member member;

            @BeforeEach
            void context() {
                member = createTestMember1();
                when(memberRepository.findByEmailAddressAndErasedFlagIsFalse(TEST_MEMBER_1_EMAIL)).thenReturn(Optional.of(member));
            }

            @Test
            void 해당_회원을_반환한다() {
                MemberDto actual = memberService.getMemberByEmail(TEST_MEMBER_1_EMAIL);

                verify(memberRepository).findByEmailAddressAndErasedFlagIsFalse(TEST_MEMBER_1_EMAIL);
                assertEquals(MemberDto.fromEntity(member), actual);
            }
        }

        @Nested
        class 유효하지_않은_이메일이_들어온_경우 {

            @BeforeEach
            void context() {
                when(memberRepository.findByEmailAddressAndErasedFlagIsFalse(TEST_INVALID_MEMBER_EMAIL)).thenReturn(Optional.empty());
            }

            @Test
            void NoSuchElementException_예외를_던진다() {
                assertThrows(NoSuchElementException.class, () -> memberService.getMemberByEmail(TEST_INVALID_MEMBER_EMAIL));
            }
        }
    }

    @Nested
    class 이메일로_회원_존재_여부_조회_시 {

        @Nested
        class 유효한_이메일이_들어온_경우 {

            @BeforeEach
            void context() {
                when(memberRepository.existsByEmailAddressAndErasedFlagIsFalse(TEST_MEMBER_1_EMAIL)).thenReturn(true);
            }

            @Test
            void true_를_반환한다() {
                boolean actual = memberService.isExist(TEST_MEMBER_1_EMAIL);

                verify(memberRepository).existsByEmailAddressAndErasedFlagIsFalse(TEST_MEMBER_1_EMAIL);
                assertTrue(actual);
            }
        }

        @Nested
        class 유효하지_않은_이메일이_들어온_경우 {

            @BeforeEach
            void context() {
                when(memberRepository.existsByEmailAddressAndErasedFlagIsFalse(TEST_INVALID_MEMBER_EMAIL)).thenReturn(false);
            }

            @Test
            void false_를_반환한다() {
                assertFalse(memberService.isExist(TEST_INVALID_MEMBER_EMAIL));
                verify(memberRepository).existsByEmailAddressAndErasedFlagIsFalse(TEST_INVALID_MEMBER_EMAIL);
            }
        }
    }

    @Nested
    class 회원_닉네임_변경_시 {

        @Nested
        class 유효한_닉네임이_들어온_경우 {

            Member prevMember;
            Member updatedMember;
            String newNickname = "newNickname";

            @BeforeEach
            void context() {
                prevMember = createMockTestMember1();
                when(memberRepository.findByEmailAddressAndErasedFlagIsFalse(TEST_MEMBER_1_EMAIL)).thenReturn(Optional.of(prevMember));

                updatedMember = createMockTestMember1();
                updatedMember.changeNickname(newNickname);
                when(memberRepository.save(any(Member.class))).thenReturn(updatedMember);
            }

            @Test
            void 해당_회원의_닉네임을_변경한다() {
                MemberDto actual = memberService.updateNickname(newNickname, prevMember.getEmailAddress());

                verify(memberRepository).save(any(Member.class));
                assertEquals(prevMember, actual.toEntity());
                assertEquals(newNickname, actual.nickname());
            }
        }

        @Nested
        class 유효하지_않은_닉네임이_들어온_경우 {

            @BeforeEach
            void context() {
                when(memberRepository.findByEmailAddressAndErasedFlagIsFalse(TEST_MEMBER_1_EMAIL)).thenReturn(
                        Optional.of(createTestMember1()));
            }

            @Test
            void IllegalArgumentException_예외를_던진다() {
                assertThrows(
                        IllegalArgumentException.class,
                        () -> memberService.updateNickname(TEST_INVALID_NICKNAME_EMPTY_STRING, createTestMember1().getEmailAddress())
                );
            }
        }
    }

    @Nested
    class 회원_삭제_시 {

        @Nested
        class 유효한_이메일이_들어온_경우 {

            Member member;

            @BeforeEach
            void context() {
                member = createTestMember1();
                when(memberRepository.findByEmailAddressAndErasedFlagIsFalse(member.getEmailAddress())).thenReturn(Optional.of(member));
                when(memberRepository.save(member)).thenReturn(member);
            }

            @Test
            void 해당_회원을_제거한다() {
                memberService.remove(member.getEmailAddress());

                verify(memberRepository).save(any(Member.class));
            }
        }

        @Nested
        class 유효하지_않은_이메일이_들어온_경우 {

            @BeforeEach
            void context() {
                doThrow(new NoSuchElementException()).when(memberService).getMemberByEmail(TEST_INVALID_MEMBER_EMAIL);
            }

            @Test
            void NoSuchElementException_예외를_던진다() {
                assertThrows(NoSuchElementException.class, () -> memberService.remove(TEST_INVALID_MEMBER_EMAIL));
            }
        }
    }
}
