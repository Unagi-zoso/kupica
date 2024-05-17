package org.nightdivers.kupica.domain.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_EMAIL;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_ID;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_NICKNAME;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nightdivers.kupica.support.annotation.RepositoryTest;
import org.nightdivers.kupica.support.factory.MemberFactory;

@RequiredArgsConstructor
@RepositoryTest
class MemberRepositoryTest {

    private final MemberRepository memberRepository;

    Member givenMember1;
    Member givenMember2;

    @BeforeEach
    void setUp() {
        givenMember1 = memberRepository.save(MemberFactory.createTestMember1());
        givenMember2 = memberRepository.save(MemberFactory.createTestMember2());
    }

    /* TARGET : 회원 단일 조회 테스트 */
    @DisplayName("nickname 과 일치하는 회원 조회 - [성공]")
    @Test
    void givenNickname_whenFindByNickname_thenSuccess() {
        // given

        // when
        Member actual = memberRepository.findByNicknameAndErasedFlagIsFalse(givenMember1.getNickname())
                .orElseThrow(NoSuchElementException::new);

        // then
        assertThat(actual).isEqualTo(givenMember1);
    }

    @DisplayName("nickname 과 일치하는 회원 조회 - [실패 : 존재하지 않는 nickname]")
    @Test
    void givenInvalidNickname_whenFindByNickname_thenThrowNoSuchElementException() {
        // given

        // when & then
        assertThatThrownBy(() -> memberRepository.findByNicknameAndErasedFlagIsFalse(TEST_INVALID_MEMBER_NICKNAME)
                .orElseThrow(NoSuchElementException::new))
                .isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("email address 와 일치하는 회원 조회 - [성공]")
    @Test
    void givenEmailAddress_whenFindByEmailAddress_thenSuccess() {
        // given

        // when
        Member actual = memberRepository.findByEmailAddressAndErasedFlagIsFalse(givenMember1.getEmailAddress())
                .orElseThrow(NoSuchElementException::new);

        // then
        assertThat(actual).isEqualTo(givenMember1);
    }

    @DisplayName("email address 와 일치하는 회원 조회 - [실패 : 존재하지 않는 email address]")
    @Test
    void givenInvalidEmailAddress_whenFindByEmailAddress_thenThrowNoSuchElementException() {
        // given

        // when & then
        assertThatThrownBy(() -> memberRepository.findByEmailAddressAndErasedFlagIsFalse(TEST_INVALID_MEMBER_EMAIL)
                .orElseThrow(NoSuchElementException::new))
                .isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("role 과 일치하는 회원 목록 조회 - [성공]")
    @Test
    void givenRole_whenFindByRole_thenSuccess() {
        // given

        // when
        List<Member> actualMembers = memberRepository.findByRoleAndErasedFlagIsFalse(givenMember1.getRole());

        // then
        assertThat(actualMembers).contains(givenMember1, givenMember2);
    }

    @DisplayName("role 과 일치하는 회원 목록 조회 - [실패 : 존재하지 않는 role]")
    @Test
    void givenInvalidRole_whenFindByRole_thenEmptyList() {
        // given

        // when
        List<Member> actualMembers = memberRepository.findByRoleAndErasedFlagIsFalse(UserRole.ADMIN);

        // then
        assertThat(actualMembers).isEmpty();
    }

    /* TARGET : 회원 존재 여부 테스트 */
    @DisplayName("id 와 일치하는 회원 존재 여부 확인 - [성공]")
    @Test
    void givenId_whenExistsById_thenSuccess() {
        // given

        // when
        boolean existedFlag = memberRepository.existsByIdAndErasedFlagIsFalse(givenMember1.getId());

        // then
        assertThat(existedFlag).isTrue();
    }

    @DisplayName("id 와 일치하는 회원 존재 여부 확인 - [실패 : 존재하지 않는 id]")
    @Test
    void givenInvalidId_whenExistsById_thenFalse() {
        // given

        // when
        boolean existedFlag = memberRepository.existsByIdAndErasedFlagIsFalse(TEST_INVALID_MEMBER_ID);

        // then
        assertThat(existedFlag).isFalse();
    }


    @DisplayName("nickname 과 일치하는 회원 존재 여부 확인 - [성공]")
    @Test
    void givenNickname_whenExistsByNickname_thenSuccess() {
        // given

        // when
        boolean existedFlag = memberRepository.existsByNicknameAndErasedFlagIsFalse(givenMember1.getNickname());

        // then
        assertThat(existedFlag).isTrue();
    }

    @DisplayName("nickname 과 일치하는 회원 존재 여부 확인 - [실패 : 존재하지 않는 nickname]")
    @Test
    void givenInvalidNickname_whenExistsByNickname_thenFalse() {
        // given

        // when
        boolean existedFlag = memberRepository.existsByNicknameAndErasedFlagIsFalse(TEST_INVALID_MEMBER_NICKNAME);

        // then
        assertThat(existedFlag).isFalse();
    }

    @DisplayName("email address 와 일치하는 회원 존재 여부 확인 - [성공]")
    @Test
    void givenEmailAddress_whenExistsByEmailAddress_thenSuccess() {
        // given

        // when
        boolean existedFlag = memberRepository.existsByEmailAddressAndErasedFlagIsFalse(givenMember1.getEmailAddress());

        // then
        assertThat(existedFlag).isTrue();
    }

    @DisplayName("email address 와 일치하는 회원 존재 여부 확인 - [실패 : 존재하지 않는 email address]")
    @Test
    void givenInvalidEmailAddress_whenExistsByEmailAddress_thenFalse() {
        // given

        // when
        boolean existedFlag = memberRepository.existsByEmailAddressAndErasedFlagIsFalse(TEST_INVALID_MEMBER_EMAIL);

        // then
        assertThat(existedFlag).isFalse();
    }
}
