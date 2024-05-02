package org.nightdivers.kupica.domain.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.nightdivers.kupica.support.constant.MemberConstant.TEST_VALID_MEMBER_EMAIL;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class MemberValidatorTest {

    /* Member validateNickname Test */
    @DisplayName("정상적인 닉네임이 주어지면 그대로 반환한다. (2자 이상 18자 이하)")
    @Test
    void givenValidNickname_whenValidateNickname_thenReturnAsItIs() {
        // given
        String givenNickname = "test nickname";

        // when
        String actual = MemberValidator.validateNickname(givenNickname);

        // then
        assertThat(actual).isEqualTo(givenNickname);
    }

    @DisplayName("닉네임이 null 이거나 빈 문자열이면 IllegalArgumentException 을 던진다.")
    @Test
    void givenEmptyNickname_whenValidateNickname_thenThrowIllegalArgumentException() {
        // given
        String givenEmptyNickname = "";
        String givenNullNickname = null;

        // when & then
        assertAll(
                () -> assertThatThrownBy(() -> MemberValidator.validateNickname(givenEmptyNickname))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("닉네임은 필수 입력값입니다."),
                () -> assertThatThrownBy(() -> MemberValidator.validateNickname(givenNullNickname))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("닉네임은 필수 입력값입니다.")
        );
    }

    @DisplayName("닉네임이 2자 미만이거나 18자 초과이면 IllegalArgumentException 을 던진다.")
    @Test
    void givenInvalidLengthNickname_whenValidateNickname_thenThrowIllegalArgumentException() {
        // given
        String givenShortNickname = "a";
        String givenLongNickname = "1234567890123456789";

        // when & then
        assertAll(
                () -> assertThatThrownBy(() -> MemberValidator.validateNickname(givenShortNickname))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("닉네임은 2자 이상 18자 이하로 입력해주세요."),
                () -> assertThatThrownBy(() -> MemberValidator.validateNickname(givenLongNickname))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("닉네임은 2자 이상 18자 이하로 입력해주세요.")
        );
    }

    /* Member validateEmailAddress Test */
    @DisplayName("정상적인 이메일 주소가 주어지면 그대로 반환한다. (5자 이상 255자 이하)")
    @Test
    void givenValidEmailAddress_whenValidateEmailAddress_thenReturnAsItIs() {
        // given
        String givenEmailAddress = TEST_VALID_MEMBER_EMAIL;

        // when
        String actual = MemberValidator.validateEmailAddress(givenEmailAddress);

        // then
        assertThat(actual).isEqualTo(givenEmailAddress);
    }

    @DisplayName("이메일 주소가 null 이거나 빈 문자열이면 IllegalArgumentException 을 던진다.")
    @Test
    void givenEmptyEmailAddress_whenValidateEmailAddress_thenThrowIllegalArgumentException() {
        // given
        String givenEmptyEmailAddress = "";
        String givenNullEmailAddress = null;

        // when & then
        assertAll(
                () -> assertThatThrownBy(
                        () -> MemberValidator.validateEmailAddress(givenEmptyEmailAddress))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("이메일 주소는 필수 입력값입니다."),
                () -> assertThatThrownBy(
                        () -> MemberValidator.validateEmailAddress(givenNullEmailAddress))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("이메일 주소는 필수 입력값입니다.")
        );
    }

    @DisplayName("이메일 주소가 5자 미만이거나 255자 초과이면 IllegalArgumentException 을 던진다.")
    @Test
    void givenInvalidLengthEmailAddress_whenValidateEmailAddress_thenThrowIllegalArgumentException() {
        // given
        String givenShortEmailAddress = "a@b.c";
        String givenLongEmailAddress = "a".repeat(256) + "@test.com";

        // when & then
        assertAll(
                () -> assertThatThrownBy(
                        () -> MemberValidator.validateEmailAddress(givenShortEmailAddress))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("이메일 주소는 6자 이상 255자 이하로 입력해주세요."),
                () -> assertThatThrownBy(
                        () -> MemberValidator.validateEmailAddress(givenLongEmailAddress))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("이메일 주소는 6자 이상 255자 이하로 입력해주세요.")
        );
    }

    @DisplayName("이메일 주소가 이메일 형식에 맞지 않으면 IllegalArgumentException 을 던진다.")
    @ParameterizedTest
    @MethodSource("org.nightdivers.kupica.support.provider.MemberProvider#invalidEmailAddressParameters")
    void givenInvalidFormatEmailAddress_whenValidateEmailAddress_thenThrowIllegalArgumentException() {
        // given
        String givenInvalidFormatEmailAddress = "invalid-email";

        // when & then
        assertThatThrownBy(
                () -> MemberValidator.validateEmailAddress(givenInvalidFormatEmailAddress))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이메일 주소 형식이 올바르지 않습니다.");
    }
}
