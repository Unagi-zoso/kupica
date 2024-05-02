package org.nightdivers.kupica.domain.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_EMAIL;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_ID;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_NICKNAME;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_VALID_MEMBER_EMAIL;

import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nightdivers.kupica.support.annotation.RepositoryTest;
import org.nightdivers.kupica.support.factory.MemberFactory;

@RequiredArgsConstructor
@RepositoryTest
class MemberRepositoryTest {
    private final MemberRepository memberRepository;
    private final EntityManager entityManager;

    Member givenMember1;
    Member givenMember2;

    @BeforeEach
    void setUp() {
        givenMember1 = memberRepository.save(MemberFactory.createTestMember1());
        givenMember2 = memberRepository.save(MemberFactory.createTestMember2());
    }

    /* 회원 조회 */
    @DisplayName("nickname 과 일치하는 회원 조회 - [성공]")
    @Test
    void givenNickname_whenFindByNickname_thenSuccess() {
        // given

        // when
        Member actual = memberRepository.findByNickname(givenMember1.getNickname())
                .orElseThrow(NoSuchElementException::new);

        // then
        assertThat(actual).isEqualTo(givenMember1);
    }

    @DisplayName("nickname 과 일치하는 회원 조회 - [실패 : 존재하지 않는 nickname]")
    @Test
    void givenInvalidNickname_whenFindByNickname_thenThrowNoSuchElementException() {
        // given

        // when & then
        assertThatThrownBy(() -> memberRepository.findByNickname(TEST_INVALID_MEMBER_NICKNAME)
                .orElseThrow(NoSuchElementException::new))
                .isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("email address 와 일치하는 회원 조회 - [성공]")
    @Test
    void givenEmailAddress_whenFindByEmailAddress_thenSuccess() {
        // given

        // when
        Member actual = memberRepository.findByEmailAddress(givenMember1.getEmailAddress())
                .orElseThrow(NoSuchElementException::new);

        // then
        assertThat(actual).isEqualTo(givenMember1);
    }

    @DisplayName("email address 와 일치하는 회원 조회 - [실패 : 존재하지 않는 email address]")
    @Test
    void givenInvalidEmailAddress_whenFindByEmailAddress_thenThrowNoSuchElementException() {
        // given

        // when & then
        assertThatThrownBy(() -> memberRepository.findByEmailAddress(TEST_INVALID_MEMBER_EMAIL)
                .orElseThrow(NoSuchElementException::new))
                .isInstanceOf(NoSuchElementException.class);
    }


    /* 회원 목록 조회 */
    @DisplayName("모든 회원 조회 - [성공]")
    @Test
    void givenMembers_whenFindAll_thenSuccess() {
        // given

        // when
        List<Member> actualMembers = memberRepository.findAll();

        // then
        assertThat(actualMembers).contains(givenMember1, givenMember2);
    }

    @DisplayName("role 과 일치하는 회원 목록 조회 - [성공]")
    @Test
    void givenRole_whenFindByRole_thenSuccess() {
        // given

        // when
        List<Member> actualMembers = memberRepository.findByRole(givenMember1.getRole());

        // then
        assertThat(actualMembers).contains(givenMember1, givenMember2);
    }

    @DisplayName("role 과 일치하는 회원 목록 조회 - [실패 : 존재하지 않는 role]")
    @Test
    void givenInvalidRole_whenFindByRole_thenEmptyList() {
        // given

        // when
        List<Member> actualMembers = memberRepository.findByRole(UserRole.ADMIN);

        // then
        assertThat(actualMembers).isEmpty();
    }


    /* 회원 등록 */
    @DisplayName("회원 등록 - [성공]")
    @Test
    void givenMember_whenSave_thenSuccess() {
        // given
        Member expected = MemberFactory.createTestMember3();

        // when
        Member actual = memberRepository.save(expected);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("회원 등록 - [실패 : 중복된 nickname]")
    @Test
    void givenDuplicatedNickname_whenSave_thenThrowConstraintViolationException() {
        // given
        Member duplicatedMember = MemberFactory.createCustomMember(givenMember1.getNickname(), TEST_VALID_MEMBER_EMAIL);
        memberRepository.save(duplicatedMember);

        // when & then
        assertThatThrownBy(entityManager::flush)
                .isInstanceOf(ConstraintViolationException.class);
    }

    @DisplayName("회원 등록 - [실패 : 중복된 email address]")
    @Test
    void givenDuplicatedEmailAddress_whenSave_thenThrowConstraintViolationException() {
        // given
        Member duplicatedMember = MemberFactory.createCustomMember("valid nickname", givenMember1.getEmailAddress());
        memberRepository.save(duplicatedMember);

        // when & then
        assertThatThrownBy(entityManager::flush)
                .isInstanceOf(ConstraintViolationException.class);
    }


    /* 회원 수정 */
    @DisplayName("회원 닉네임 수정 - [성공]")
    @Test
    void givenMember_whenChangeNickname_thenSuccess() {
        // given
        Member prevMember = memberRepository.save(MemberFactory.createTestMember3());
        LocalDateTime prevUpdatedDatetime = prevMember.getUpdatedDatetime();

        // when
        prevMember.changeNickname("newNickname");
        entityManager.flush();

        Member actual = memberRepository.findById(prevMember.getId()).orElseThrow(NoSuchElementException::new);

        // then
        assertAll(
                () -> assertThat(actual).isEqualTo(prevMember),
                () -> assertThat(actual.getNickname()).isEqualTo("newNickname"),
                () -> assertThat(actual.getUpdatedDatetime()).isNotEqualTo(prevUpdatedDatetime)
        );
    }

    @DisplayName("회원 닉네임 수정 - [실패 : 중복된 nickname]")
    @Test
    void givenDuplicatedNickname_whenChangeNickname_thenThrowConstraintViolationException() {
        // given
        Member duplicatedMember = memberRepository.save(MemberFactory.createTestMember3());

        // when
        duplicatedMember.changeNickname(givenMember1.getNickname());

        // then
        assertThatThrownBy(entityManager::flush)
                .isInstanceOf(ConstraintViolationException.class);

    }

    @DisplayName("회원 닉네임 수정 - [실패 : nickname 최대 길이 초과]")
    @Test
    void givenMemberWithTooLongNickname_whenChangeNickname_thenThrowDataException() {
        // given
        Member memberWithTooLongNickname = memberRepository.save(MemberFactory.createTestMember3());

        // when
        memberWithTooLongNickname.changeNickname("a".repeat(19));

        // then
        assertThatThrownBy(entityManager::flush)
                .isInstanceOf(DataException.class);
    }


    /* 회원 삭제 */
    @DisplayName("회원 삭제 - [성공]")
    @Test
    void givenMember_whenDelete_thenSuccess() {
        // given
        Member givenMember = memberRepository.save(MemberFactory.createTestMember3());

        // when
        memberRepository.delete(givenMember);

        // then
        assertThatThrownBy(
                () -> memberRepository.findById(givenMember.getId()).orElseThrow(NoSuchElementException::new))
                .isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("id 와 일치하는 회원 삭제 - [성공]")
    @Test
    void givenId_whenDeleteById_thenSuccess() {
        // given
        Member givenMember = memberRepository.save(MemberFactory.createTestMember3());

        // when
        memberRepository.deleteById(givenMember.getId());

        // then
        assertThatThrownBy(
                () -> memberRepository.findById(givenMember.getId()).orElseThrow(NoSuchElementException::new))
                .isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("id 와 일치하는 회원 삭제 - [실패 : 존재하지 않는 id]")
    @Test
    void givenInvalidId_whenDeleteById_thenNoEffect() {
        // given
        int prevSize = memberRepository.findAll().size();

        // when
        memberRepository.deleteById(TEST_INVALID_MEMBER_ID);
        int currentSize = memberRepository.findAll().size();

        // then
        assertThat(currentSize).isEqualTo(prevSize);
    }


    /* 회원 존재 여부 확인 */
    @DisplayName("id 와 일치하는 회원 존재 여부 확인 - [성공]")
    @Test
    void givenId_whenExistsById_thenSuccess() {
        // given

        // when
        boolean existedFlag = memberRepository.existsById(1L);

        // then
        assertThat(existedFlag).isTrue();
    }

    @DisplayName("id 와 일치하는 회원 존재 여부 확인 - [실패 : 존재하지 않는 id]")
    @Test
    void givenInvalidId_whenExistsById_thenFalse() {
        // given

        // when
        boolean existedFlag = memberRepository.existsById(TEST_INVALID_MEMBER_ID);

        // then
        assertThat(existedFlag).isFalse();
    }


    @DisplayName("nickname 과 일치하는 회원 존재 여부 확인 - [성공]")
    @Test
    void givenNickname_whenExistsByNickname_thenSuccess() {
        // given

        // when
        boolean existedFlag = memberRepository.existsByNickname(givenMember1.getNickname());

        // then
        assertThat(existedFlag).isTrue();
    }

    @DisplayName("nickname 과 일치하는 회원 존재 여부 확인 - [실패 : 존재하지 않는 nickname]")
    @Test
    void givenInvalidNickname_whenExistsByNickname_thenFalse() {
        // given

        // when
        boolean existedFlag = memberRepository.existsByNickname(TEST_INVALID_MEMBER_NICKNAME);

        // then
        assertThat(existedFlag).isFalse();
    }

    @DisplayName("email address 와 일치하는 회원 존재 여부 확인 - [성공]")
    @Test
    void givenEmailAddress_whenExistsByEmailAddress_thenSuccess() {
        // given

        // when
        boolean existedFlag = memberRepository.existsByEmailAddress(givenMember1.getEmailAddress());

        // then
        assertThat(existedFlag).isTrue();
    }

    @DisplayName("email address 와 일치하는 회원 존재 여부 확인 - [실패 : 존재하지 않는 email address]")
    @Test
    void givenInvalidEmailAddress_whenExistsByEmailAddress_thenFalse() {
        // given

        // when
        boolean existedFlag = memberRepository.existsByEmailAddress(TEST_INVALID_MEMBER_EMAIL);

        // then
        assertThat(existedFlag).isFalse();
    }
}
