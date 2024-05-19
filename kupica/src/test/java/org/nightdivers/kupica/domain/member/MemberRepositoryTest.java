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
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.nightdivers.kupica.support.annotation.RepositoryTest;
import org.nightdivers.kupica.support.factory.MemberFactory;

@DisplayNameGeneration(ReplaceUnderscores.class)
@RequiredArgsConstructor
@RepositoryTest
class MemberRepositoryTest {

    private final MemberRepository memberRepository;

    Member givenMember1;
    Member givenMember2;

    @BeforeEach
    void setUp() {
        initTestData();
    }

    @Nested
    class 회원_id_와_일치하는_회원을_조회할_시 {

        @Nested
        class 존재하는_경우 {

            @Test
            void 해당되는_회원을_반환한다() {
                Member actual = memberRepository.findByIdAndErasedFlagIsFalse(givenMember1.getId())
                        .orElseThrow(NoSuchElementException::new);

                assertThat(actual).isEqualTo(givenMember1);
            }
        }

        @Nested
        class 존재하지_않는_경우 {

            @Test
            void NoSuchElementException_예외를_발생시킨다() {
                assertThatThrownBy(() -> memberRepository.findByIdAndErasedFlagIsFalse(TEST_INVALID_MEMBER_ID)
                        .orElseThrow(NoSuchElementException::new))
                        .isInstanceOf(NoSuchElementException.class);
            }
        }
    }

    @Nested
    class 회원_nickname_과_일치하는_회원을_조회할_시 {

        @Nested
        class 존재하는_경우 {

            @Test
            void 해당되는_회원을_반환한다() {
                Member actual = memberRepository.findByNicknameAndErasedFlagIsFalse(givenMember1.getNickname())
                        .orElseThrow(NoSuchElementException::new);

                assertThat(actual).isEqualTo(givenMember1);
            }
        }

        @Nested
        class 존재하지_않는_경우 {

            @Test
            void NoSuchElementException_예외를_발생시킨다() {
                assertThatThrownBy(() -> memberRepository.findByNicknameAndErasedFlagIsFalse(TEST_INVALID_MEMBER_NICKNAME)
                        .orElseThrow(NoSuchElementException::new))
                        .isInstanceOf(NoSuchElementException.class);
            }
        }
    }

    @Nested
    class 회원_email_address_와_일치하는_회원을_조회할_시 {

        @Nested
        class 존재하는_경우 {

            @Test
            void 해당되는_회원을_반환한다() {
                Member actual = memberRepository.findByEmailAddressAndErasedFlagIsFalse(givenMember1.getEmailAddress())
                        .orElseThrow(NoSuchElementException::new);

                assertThat(actual).isEqualTo(givenMember1);
            }
        }

        @Nested
        class 존재하지_않는_경우 {

            @Test
            void NoSuchElementException_예외를_발생시킨다() {
                assertThatThrownBy(() -> memberRepository.findByEmailAddressAndErasedFlagIsFalse(TEST_INVALID_MEMBER_EMAIL)
                        .orElseThrow(NoSuchElementException::new))
                        .isInstanceOf(NoSuchElementException.class);
            }
        }
    }

    @Nested
    class 회원_role_과_일치하는_회원을_조회할_시 {

        @Nested
        class 존재하는_경우 {

            @Test
            void 해당되는_회원_목록을_반환한다() {
                List<Member> actualMembers = memberRepository.findByRoleAndErasedFlagIsFalse(givenMember1.getRole());

                assertThat(actualMembers).contains(givenMember1, givenMember2);
            }
        }

        @Nested
        class 존재하지_않는_경우 {

            @Test
            void 빈_목록을_반환한다() {
                List<Member> actualMembers = memberRepository.findByRoleAndErasedFlagIsFalse(UserRole.ADMIN);

                assertThat(actualMembers).isEmpty();
            }
        }
    }

    @Nested
    class 회원_id_와_일치하는_회원_존재_여부를_조회할_시 {

        @Nested
        class 존재하는_경우 {

            @Test
            void true_를_반환한다() {
                boolean existedFlag = memberRepository.existsByIdAndErasedFlagIsFalse(givenMember1.getId());

                assertThat(existedFlag).isTrue();
            }
        }

        @Nested
        class 존재하지_않는_경우 {

            @Test
            void false_를_반환한다() {
                boolean existedFlag = memberRepository.existsByIdAndErasedFlagIsFalse(TEST_INVALID_MEMBER_ID);

                assertThat(existedFlag).isFalse();
            }
        }
    }

    @Nested
    class 회원_nickname_과_일치하는_회원_존재_여부를_조회할_시 {

        @Nested
        class 존재하는_경우 {

            @Test
            void true_를_반환한다() {
                boolean existedFlag = memberRepository.existsByNicknameAndErasedFlagIsFalse(givenMember1.getNickname());

                assertThat(existedFlag).isTrue();
            }
        }

        @Nested
        class 존재하지_않는_경우 {

            @Test
            void false_를_반환한다() {
                boolean existedFlag = memberRepository.existsByNicknameAndErasedFlagIsFalse(TEST_INVALID_MEMBER_NICKNAME);

                assertThat(existedFlag).isFalse();
            }
        }
    }

    @Nested
    class 회원_email_address_와_일치하는_회원_존재_여부를_조회할_시 {

        @Nested
        class 존재하는_경우 {

            @Test
            void true_를_반환한다() {
                boolean existedFlag = memberRepository.existsByEmailAddressAndErasedFlagIsFalse(givenMember1.getEmailAddress());

                assertThat(existedFlag).isTrue();
            }
        }

        @Nested
        class 존재하지_않는_경우 {

            @Test
            void false_를_반환한다() {
                boolean existedFlag = memberRepository.existsByEmailAddressAndErasedFlagIsFalse(TEST_INVALID_MEMBER_EMAIL);

                assertThat(existedFlag).isFalse();
            }
        }
    }

    private void initTestData() {
        givenMember1 = memberRepository.save(MemberFactory.createTestMember1());
        givenMember2 = memberRepository.save(MemberFactory.createTestMember2());
    }
}
