package org.nightdivers.kupica.domain.anonymoususer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.DUPLICATED_TEST_ANONYMOUS_COUNT;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_2_IP_ADDRESS;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_ANONYMOUS_USER_2_NICKNAME;
import static org.nightdivers.kupica.support.constant.AnonymousUserConstant.TEST_INVALID_ANONYMOUS_USER_NICKNAME;
import static org.nightdivers.kupica.support.factory.AnonymousUserFactory.createDuplicatedAnonymousUsers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.nightdivers.kupica.support.annotation.RepositoryTest;
import org.nightdivers.kupica.support.factory.AnonymousUserFactory;

@DisplayNameGeneration(ReplaceUnderscores.class)
@RequiredArgsConstructor
@RepositoryTest
class AnonymousUserRepositoryTest {

    private final AnonymousUserRepository anonymousUserRepository;

    List<AnonymousUser> givenDuplicatedAnonymousUsers;

    @BeforeEach
    void setUp() {
        givenDuplicatedAnonymousUsers = anonymousUserRepository.saveAll(
                createDuplicatedAnonymousUsers(AnonymousUserFactory::createTestAnonymousUser1, DUPLICATED_TEST_ANONYMOUS_COUNT)
        );
    }

    @Nested
    class 익명_이용자_nickname_과_일치하는_익명_이용자_목록_조회_시 {

        @Nested
        class 일치하는_경우 {

            @Test
            void 해당되는_익명_이용자_목록을_반환한다() {
                List<AnonymousUser> actualAnonymousUsers = anonymousUserRepository.findAllByNickname(
                        givenDuplicatedAnonymousUsers.getFirst().getNickname());

                assertAll(
                        () -> assertThat(actualAnonymousUsers).containsAll(givenDuplicatedAnonymousUsers),
                        () -> assertThat(actualAnonymousUsers.size()).isGreaterThanOrEqualTo(DUPLICATED_TEST_ANONYMOUS_COUNT)
                );
            }
        }

        @Nested
        class 일치하지_않는_경우 {

            @Test
            void 빈_목록을_반환한다() {
                assertThat(anonymousUserRepository.findAllByNickname(TEST_INVALID_ANONYMOUS_USER_NICKNAME)).isEmpty();
            }
        }
    }

    @Nested
    class 익명_이용자_ip_address_와_일치하는_익명_이용자_목록_조회_시 {

        @Nested
        class 일치하는_경우 {

            @Test
            void 해당되는_익명_이용자_목록을_반환한다() {
                List<AnonymousUser> actualAnonymousUsers = anonymousUserRepository.findAllByIpAddress(
                        givenDuplicatedAnonymousUsers.getFirst().getIpAddress());

                assertAll(
                        () -> assertThat(actualAnonymousUsers).containsAll(givenDuplicatedAnonymousUsers),
                        () -> assertThat(actualAnonymousUsers.size()).isGreaterThanOrEqualTo(DUPLICATED_TEST_ANONYMOUS_COUNT)
                );
            }
        }

        @Nested
        class 일치하지_않는_경우 {

            @Test
            void 빈_목록을_반환한다() {
                assertThat(anonymousUserRepository.findAllByIpAddress(TEST_INVALID_ANONYMOUS_USER_NICKNAME)).isEmpty();
            }
        }
    }

    @Nested
    class 익명_이용자_nickname_과_ip_address_와_일치하는_익명_이용자_목록_조회_시 {

        @Nested
        class 일치하는_경우 {

            @BeforeEach
            void context() {
                anonymousUserRepository.save(
                        AnonymousUserFactory.createCustomAnonymousUser(
                                givenDuplicatedAnonymousUsers.getFirst().getNickname(),
                                TEST_ANONYMOUS_USER_2_IP_ADDRESS
                        )
                );
                anonymousUserRepository.save(
                        AnonymousUserFactory.createCustomAnonymousUser(
                                TEST_ANONYMOUS_USER_2_NICKNAME,
                                givenDuplicatedAnonymousUsers.getFirst().getIpAddress()
                        )
                );
            }

            @Test
            void 해당되는_익명_이용자_목록을_반환한다() {
                List<AnonymousUser> actualAnonymousUsers = anonymousUserRepository.findAllByNicknameAndIpAddress(
                        givenDuplicatedAnonymousUsers.getFirst().getNickname(),
                        givenDuplicatedAnonymousUsers.getFirst().getIpAddress()
                );

                assertAll(
                        () -> assertThat(actualAnonymousUsers).containsAll(givenDuplicatedAnonymousUsers),
                        () -> assertThat(actualAnonymousUsers.size()).isEqualTo(DUPLICATED_TEST_ANONYMOUS_COUNT)
                );
            }
        }

        @Nested
        class 일치하지_않는_경우 {

            @Test
            void 빈_목록을_반환한다() {
                assertThat(anonymousUserRepository.findAllByNicknameAndIpAddress(
                        TEST_INVALID_ANONYMOUS_USER_NICKNAME, TEST_INVALID_ANONYMOUS_USER_NICKNAME)).isEmpty();
            }
        }
    }
}
