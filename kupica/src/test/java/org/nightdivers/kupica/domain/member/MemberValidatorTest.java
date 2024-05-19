package org.nightdivers.kupica.domain.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_VALID_MEMBER_EMAIL;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.ActiveProfiles;

@DisplayNameGeneration(ReplaceUnderscores.class)
@ActiveProfiles("test")
public class MemberValidatorTest {

    @Nested
    class nickname_유효성_검사_시 {

        @Nested
        class 정상적인_nickname_이_주어지는_경우 {

            String givenNickname = "test nickname";;

            @Test
            void 원본_nickname_을_반환한다() {
                String actual = MemberValidator.validateNickname(givenNickname);

                assertThat(actual).isEqualTo(givenNickname);
            }
        }

        @Nested
        class nickname_이_null_또는_빈_문자열인_경우 {

            String givenEmptyNickname = "";
            String givenNullNickname = null;

            @Test
            void IllegalArgumentException_을_던진다() {
                assertAll(
                        () -> assertThatThrownBy(() -> MemberValidator.validateNickname(givenEmptyNickname))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessage("닉네임은 필수 입력값입니다."),
                        () -> assertThatThrownBy(() -> MemberValidator.validateNickname(givenNullNickname))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessage("닉네임은 필수 입력값입니다.")
                );
            }
        }

        @Nested
        class nickname_이_2자_미만이거나_18자_초과인_경우 {

            String givenShortNickname = "a";
            String givenLongNickname = "1234567890123456789";

            @Test
            void IllegalArgumentException_을_던진다() {
                assertAll(
                        () -> assertThatThrownBy(() -> MemberValidator.validateNickname(givenShortNickname))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessage("닉네임은 2자 이상 18자 이하로 입력해주세요."),
                        () -> assertThatThrownBy(() -> MemberValidator.validateNickname(givenLongNickname))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessage("닉네임은 2자 이상 18자 이하로 입력해주세요.")
                );
            }
        }
    }
    
    @Nested
    class email_address_유효성_검사_시 {

        @Nested
        class 정상적인_email_address_가_주어지는_경우 {

            String givenEmailAddress = TEST_VALID_MEMBER_EMAIL;

            @Test
            void 원본_email_address_을_반환한다() {
                String actual = MemberValidator.validateEmailAddress(givenEmailAddress);

                assertThat(actual).isEqualTo(givenEmailAddress);
            }
        }

        @Nested
        class email_address_가_null_또는_빈_문자열인_경우 {

            String givenEmptyEmailAddress = "";
            String givenNullEmailAddress = null;

            @Test
            void IllegalArgumentException_을_던진다() {
                assertAll(
                        () -> assertThatThrownBy(() -> MemberValidator.validateEmailAddress(givenEmptyEmailAddress))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessage("이메일 주소는 필수 입력값입니다."),
                        () -> assertThatThrownBy(() -> MemberValidator.validateEmailAddress(givenNullEmailAddress))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessage("이메일 주소는 필수 입력값입니다.")
                );
            }
        }

        @Nested
        class email_address_가_5자_미만이거나_255자_초과인_경우 {

            String givenShortEmailAddress = "a@b.c";
            String givenLongEmailAddress = "a".repeat(256) + "@test.com";

            @Test
            void IllegalArgumentException_을_던진다() {
                assertAll(
                        () -> assertThatThrownBy(() -> MemberValidator.validateEmailAddress(givenShortEmailAddress))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessage("이메일 주소는 6자 이상 255자 이하로 입력해주세요."),
                        () -> assertThatThrownBy(() -> MemberValidator.validateEmailAddress(givenLongEmailAddress))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessage("이메일 주소는 6자 이상 255자 이하로 입력해주세요.")
                );
            }
        }

        @Nested
        class email_address_가_email_형식에_맞지_않는_경우 {

            @ParameterizedTest
            @MethodSource("org.nightdivers.kupica.support.provider.MemberProvider#invalidEmailAddressParameters")
            void IllegalArgumentException_을_던진다(String givenInvalidFormatEmailAddress) {
                assertThatThrownBy(() -> MemberValidator.validateEmailAddress(givenInvalidFormatEmailAddress))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("이메일 주소");
            }
        }
    }
}
