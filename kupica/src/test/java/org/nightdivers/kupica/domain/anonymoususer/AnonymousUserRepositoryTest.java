package org.nightdivers.kupica.domain.anonymoususer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.DUPLICATED_TEST_ANONYMOUS_COUNT;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_1_IP_ADDRESS;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_1_NICKNAME;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_1_PW;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_2_IP_ADDRESS;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_2_NICKNAME;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_INVALID_ANONYMOUS_USER_ID;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_INVALID_ANONYMOUS_USER_NICKNAME;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nightdivers.kupica.support.annotation.RepositoryTest;
import org.nightdivers.kupica.support.factory.AnonymousUserFactory;

@RequiredArgsConstructor
@RepositoryTest
class AnonymousUserRepositoryTest {
    private final AnonymousUserRepository anonymousUserRepository;

    List<AnonymousUser> givenDuplicatedAnonymousUsers;

    @BeforeEach
    void setUp() {
        givenDuplicatedAnonymousUsers = anonymousUserRepository.saveAll(
                AnonymousUserFactory.createDuplicatedAnonymousUsers(AnonymousUserFactory::createTestAnonymousUser1, DUPLICATED_TEST_ANONYMOUS_COUNT)
        );
    }

    /* 익명 사용자 목록 조회 */
    @DisplayName("nickname 과 일치하는 익명 사용자 목록 조회 - [성공]")
    @Test
    void givenDuplicatedAnonymousUsers_whenFindAllByNickname_thenSuccess() {
        // given

        // when
        List<AnonymousUser> actualAnonymousUsers = anonymousUserRepository.findAllByNickname(givenDuplicatedAnonymousUsers.getFirst().getNickname());

        // then
        assertThat(actualAnonymousUsers).containsAll(givenDuplicatedAnonymousUsers);
        assertThat(actualAnonymousUsers.size()).isGreaterThanOrEqualTo(DUPLICATED_TEST_ANONYMOUS_COUNT);
    }

    @DisplayName("nickname 과 일치하는 익명 사용자 목록 조회 - [실패 : 일치하는 nickname 없음]")
    @Test
    void givenDuplicatedAnonymousUsers_whenFindAllByNickname_thenEmpty() {
        // given

        // when
        List<AnonymousUser> actualAnonymousUsers = anonymousUserRepository.findAllByNickname(TEST_INVALID_ANONYMOUS_USER_NICKNAME);

        // then
        assertThat(actualAnonymousUsers).isEmpty();
    }

    @DisplayName("ip address 와 일치하는 익명 사용자 목록 조회 - [성공]")
    @Test
    void givenDuplicatedAnonymousUsers_whenFindAllByIpAddress_thenSuccess() {
        // given

        // when
        List<AnonymousUser> actualAnonymousUsers = anonymousUserRepository.findAllByIpAddress(givenDuplicatedAnonymousUsers.getFirst().getIpAddress());

        // then
        assertThat(actualAnonymousUsers).containsAll(givenDuplicatedAnonymousUsers);
        assertThat(actualAnonymousUsers.size()).isGreaterThanOrEqualTo(DUPLICATED_TEST_ANONYMOUS_COUNT);
    }

    @DisplayName("ip address 와 일치하는 익명 사용자 목록 조회 - [실패 : 일치하는 익명 사용자 없음]")
    @Test
    void givenDuplicatedAnonymousUsers_whenFindAllByIpAddress_thenEmpty() {
        // given

        // when
        List<AnonymousUser> actualAnonymousUsers = anonymousUserRepository.findAllByIpAddress(TEST_INVALID_ANONYMOUS_USER_NICKNAME);

        // then
        assertThat(actualAnonymousUsers).isEmpty();
    }

    @DisplayName("nickname 과 ip address 와 일치하는 익명 사용자 목록 조회 - [성공]")
    @Test
    void givenDuplicatedAnonymousUsers_whenFindAllByNicknameAndIpAddress_thenSuccess() {
        // given
        // 테스트 익명 사용자 1 의 닉네임 과 테스트 익명 사용자 2 의 ip address 가 일치하는 익명 사용자 생성
        anonymousUserRepository.save(AnonymousUserFactory.createCustomAnonymousUsers(TEST_ANONYMOUS_USER_1_NICKNAME,
                TEST_ANONYMOUS_USER_1_PW, TEST_ANONYMOUS_USER_2_IP_ADDRESS));
        // 테스트 익명 사용자 2 의 닉네임 과 테스트 익명 사용자 1 의 ip address 가 일치하는 익명 사용자 생성
        anonymousUserRepository.save(AnonymousUserFactory.createCustomAnonymousUsers(TEST_ANONYMOUS_USER_2_NICKNAME,
                TEST_ANONYMOUS_USER_1_PW, TEST_ANONYMOUS_USER_1_IP_ADDRESS));

        // when
        List<AnonymousUser> actualAnonymousUsers = anonymousUserRepository.findAllByNicknameAndIpAddress(TEST_ANONYMOUS_USER_1_NICKNAME, TEST_ANONYMOUS_USER_1_IP_ADDRESS);

        // then
        assertThat(actualAnonymousUsers).containsAll(givenDuplicatedAnonymousUsers);
        assertThat(actualAnonymousUsers.size()).isEqualTo(DUPLICATED_TEST_ANONYMOUS_COUNT);
    }

    @DisplayName("모든 익명 사용자 조회 - [성공]")
    @Test
    void givenAnonymousUsers_whenFindAll_thenSuccess() {
        // given

        // when
        List<AnonymousUser> actualAnonymousUsers = anonymousUserRepository.findAll();

        // then
        assertThat(actualAnonymousUsers).containsAll(givenDuplicatedAnonymousUsers);
        assertThat(actualAnonymousUsers.size()).isGreaterThan(DUPLICATED_TEST_ANONYMOUS_COUNT);
    }


    /* 익명 사용자 등록 */
    @DisplayName("익명 사용자 등록 - [성공]")
    @Test
    void givenAnonymousUser_whenSave_thenSuccess() {
        // given
        AnonymousUser expected = AnonymousUserFactory.createTestAnonymousUser3();

        // when
        AnonymousUser actual = anonymousUserRepository.save(expected);

        // then
        assertThat(actual).isEqualTo(expected);
    }


    /* 익명 사용자 삭제 */
    @DisplayName("익명 사용자 삭제 - [성공]")
    @Test
    void givenAnonymousUser_whenDelete_thenSuccess() {
        // given
        AnonymousUser givenAnonymousUser = givenDuplicatedAnonymousUsers.getFirst();

        // when
        anonymousUserRepository.delete(givenAnonymousUser);

        // then
        assertThatThrownBy(() -> anonymousUserRepository.findById(givenAnonymousUser.getId()).orElseThrow(NoSuchElementException::new))
                .isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("익명 사용자 삭제 - [실패 : 존재하지 않는 익명 사용자]")
    @Test
    void givenAnonymousUser_whenDelete_thenNotEffect() {
        // given
        int previousSize = anonymousUserRepository.findAll().size();

        // when
        anonymousUserRepository.deleteById(TEST_INVALID_ANONYMOUS_USER_ID);
        int currentSize = anonymousUserRepository.findAll().size();

        // then
        assertThat(currentSize).isEqualTo(previousSize);
    }
}
