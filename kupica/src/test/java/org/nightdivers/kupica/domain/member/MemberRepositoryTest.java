package org.nightdivers.kupica.domain.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_EMAIL;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_ID;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_INVALID_MEMBER_NICKNAME;

import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nightdivers.kupica.support.annotation.RepositoryTest;
import org.nightdivers.kupica.support.generator.MemberGenerator;

@RequiredArgsConstructor
@RepositoryTest
class MemberRepositoryTest {
    private final MemberRepository memberRepository;
    private final EntityManager entityManager;

    Member givenMember1;
    Member givenMember2;

    @BeforeEach
    void setUp() {
        givenMember1 = memberRepository.save(MemberGenerator.createTestMember1());
        givenMember2 = memberRepository.save(MemberGenerator.createTestMember2());
    }

    /* 회원 조회 */
    @DisplayName("nickname 과 일치하는 회원 조회")
    @Test
    void testFindByNickname() {
        // given

        // when
        Member expected = memberRepository.findByNickname(givenMember1.getNickname()).orElseThrow(NoSuchElementException::new);

        // then
        assertThat(givenMember1).isEqualTo(expected);
    }

    @DisplayName("email address 와 일치하는 회원 조회")
    @Test
    void testFindByEmailAddress() {
        // given

        // when
        Member expected = memberRepository.findByEmailAddress(givenMember1.getEmailAddress()).orElseThrow(NoSuchElementException::new);

        // then
        assertThat(givenMember1).isEqualTo(expected);
    }


    /* 회원 목록 조회 */
    @DisplayName("모든 회원 조회")
    @Test
    void testFindAll() {
        // given

        // when
        Iterable<Member> members = memberRepository.findAll();

        // then
        assertThat(members).contains(givenMember1, givenMember2);
    }

    @DisplayName("role 과 일치하는 회원 목록 조회")
    @Test
    void testFindByRole() {
        // given

        // when
        Iterable<Member> members = memberRepository.findByRole(givenMember1.getRole());

        // then
        assertThat(members).contains(givenMember1, givenMember2);
    }


    /* 회원 등록 */
    @DisplayName("회원 등록")
    @Test
    void testSaveMember() {
        // given
        Member expected = MemberGenerator.createTestMember3();

        // when
        Member actual = memberRepository.save(expected);

        // then
        assertThat(actual).isEqualTo(expected);
    }


    /* 회원 수정 */
    @DisplayName("회원 수정")
    @Test
    void testUpdateMember() {
        // given
        Member prevMember = memberRepository.save(MemberGenerator.createTestMember3());

        // when
        prevMember.changeNickname("newNickname");
        LocalDateTime prevUpdatedDatetime = prevMember.getUpdatedDatetime();
        entityManager.flush();

        Member actual = memberRepository.findById(prevMember.getId()).orElseThrow(NoSuchElementException::new);

        // then
        assertThat(actual).isEqualTo(prevMember);
        assertThat(actual.getNickname()).isEqualTo("newNickname");
        assertThat(actual.getUpdatedDatetime()).isNotEqualTo(prevUpdatedDatetime);
    }

    /* 회원 삭제 */
    @DisplayName("회원 삭제")
    @Test
    void testDeleteMember() {
        // given
        Member member = memberRepository.save(MemberGenerator.createTestMember3());

        // when
        memberRepository.delete(member);
        entityManager.flush();
        entityManager.clear();

        // then
        assertThat(memberRepository.findById(member.getId())).isEmpty();
    }


    /* 회원 존재 여부 확인 */
    @DisplayName("id 와 일치하는 회원 존재 여부 확인 - [성공]")
    @Test
    void testExistsById() {
        // given

        // when
        boolean existedFlag = memberRepository.existsById(1L);

        // then
        assertThat(existedFlag).isTrue();
    }

    @DisplayName("id 와 일치하는 회원 존재 여부 확인 - [실패]")
    @Test
    void testNotExistsById() {
        // given

        // when
        boolean existedFlag = memberRepository.existsById(TEST_INVALID_MEMBER_ID);

        // then
        assertThat(existedFlag).isFalse();
    }


    @DisplayName("nickname 과 일치하는 회원 존재 여부 확인 - [성공]")
    @Test
    void testExistsByNickname() {
        // given

        // when
        boolean existedFlag = memberRepository.existsByNickname(givenMember1.getNickname());

        // then
        assertThat(existedFlag).isTrue();
    }

    @DisplayName("nickname 과 일치하는 회원 존재 여부 확인 - [실패]")
    @Test
    void testNotExistsByNickname() {
        // given

        // when
        boolean existedFlag = memberRepository.existsByNickname(TEST_INVALID_MEMBER_NICKNAME);

        // then
        assertThat(existedFlag).isFalse();
    }

    @DisplayName("email address 와 일치하는 회원 존재 여부 확인 - [성공]")
    @Test
    void testExistsByEmailAddress() {
        // given

        // when
        boolean existedFlag = memberRepository.existsByEmailAddress(givenMember1.getEmailAddress());

        // then
        assertThat(existedFlag).isTrue();
    }

    @DisplayName("email address 와 일치하는 회원 존재 여부 확인 - [실패]")
    @Test
    void testNotExistsByEmailAddress() {
        // given

        // when
        boolean existedFlag = memberRepository.existsByEmailAddress(TEST_INVALID_MEMBER_EMAIL);

        // then
        assertThat(existedFlag).isFalse();
    }
}
