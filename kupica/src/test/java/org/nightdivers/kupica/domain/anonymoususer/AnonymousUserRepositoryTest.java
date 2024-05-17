package org.nightdivers.kupica.domain.anonymoususer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.DUPLICATED_TEST_ANONYMOUS_COUNT;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_1_IP_ADDRESS;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_1_NICKNAME;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_1_PW;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_2_IP_ADDRESS;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_2_NICKNAME;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_INVALID_ANONYMOUS_USER_NICKNAME;
import static org.nightdivers.kupica.support.factory.AnonymousUserFactory.createDuplicatedAnonymousUsers;

import java.util.List;
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
                createDuplicatedAnonymousUsers(AnonymousUserFactory::createTestAnonymousUser1, DUPLICATED_TEST_ANONYMOUS_COUNT));
    }

    /* TARGET : 익명 사용자 목록 조회 테스트 */
    @DisplayName("nickname 과 일치하는 익명 사용자 목록 조회 - [성공]")
    @Test
    void givenDuplicatedAnonymousUsers_whenFindAllByNickname_thenSuccess() {
        // given

        // when
        List<AnonymousUser> actualAnonymousUsers = anonymousUserRepository.findAllByNickname(
                givenDuplicatedAnonymousUsers.getFirst().getNickname());

        // then
        assertAll(
                () -> assertThat(actualAnonymousUsers).containsAll(givenDuplicatedAnonymousUsers),
                () -> assertThat(actualAnonymousUsers.size()).isGreaterThanOrEqualTo(DUPLICATED_TEST_ANONYMOUS_COUNT)
        );
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
        List<AnonymousUser> actualAnonymousUsers = anonymousUserRepository.findAllByIpAddress(
                givenDuplicatedAnonymousUsers.getFirst().getIpAddress());

        // then
        assertAll(
                () -> assertThat(actualAnonymousUsers).containsAll(givenDuplicatedAnonymousUsers),
                () -> assertThat(actualAnonymousUsers.size()).isGreaterThanOrEqualTo(DUPLICATED_TEST_ANONYMOUS_COUNT)
        );
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
        anonymousUserRepository.save(
                AnonymousUserFactory.createCustomAnonymousUser(
                        TEST_ANONYMOUS_USER_1_NICKNAME,
                        TEST_ANONYMOUS_USER_1_PW,
                        TEST_ANONYMOUS_USER_2_IP_ADDRESS
                )
        );
        // 테스트 익명 사용자 2 의 닉네임 과 테스트 익명 사용자 1 의 ip address 가 일치하는 익명 사용자 생성
        anonymousUserRepository.save(
                AnonymousUserFactory.createCustomAnonymousUser(
                        TEST_ANONYMOUS_USER_2_NICKNAME,
                        TEST_ANONYMOUS_USER_1_PW,
                        TEST_ANONYMOUS_USER_1_IP_ADDRESS
                )
        );

        // when
        List<AnonymousUser> actualAnonymousUsers = anonymousUserRepository.findAllByNicknameAndIpAddress(
                TEST_ANONYMOUS_USER_1_NICKNAME, TEST_ANONYMOUS_USER_1_IP_ADDRESS);

        // then
        assertAll(
                () -> assertThat(actualAnonymousUsers).containsAll(givenDuplicatedAnonymousUsers),
                () -> assertThat(actualAnonymousUsers.size()).isEqualTo(DUPLICATED_TEST_ANONYMOUS_COUNT)
        );
    }
}
