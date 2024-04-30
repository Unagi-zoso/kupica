package org.nightdivers.kupica.domain.hashtag;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HashtagValidatorTest {

    /* Hashtag validateTagName Test */
    @DisplayName("# 가 앞에 붙지 않은 tagname 이 주어지면 #를 붙여서 반환한다.")
    @Test
    void givenTagNameWithoutHash_whenValidateTagName_thenAddHash() {
        // given
        String givenTagName = "test tag name";

        // when
        Hashtag actual = Hashtag.of(givenTagName);

        // then
        assertThat(actual.getTagName()).isEqualTo("#" + givenTagName);
    }

    @DisplayName("# 가 앞에 붙은 tagname 이 주어지면 그대로 반환한다.")
    @Test
    void givenTagNameWithHash_whenValidateTagName_thenReturnAsItIs() {
        // given
        String givenTagName = "#test tag name";

        // when
        Hashtag actual = Hashtag.of(givenTagName);

        // then
        assertThat(actual.getTagName()).isEqualTo(givenTagName);
    }

    @DisplayName("tagname 이 null 이거나 빈 문자열이면 IllegalArgumentException 을 던진다.")
    @Test
    void givenEmptyTagName_whenValidateTagName_thenThrowIllegalArgumentException() {
        // given
        String givenEmptyTagName = "";
        String givenNullTagName = null;

        // when & then
        assertAll(
                () -> assertThatThrownBy(() -> Hashtag.of(givenEmptyTagName))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("Tag name must not be empty"),
                () -> assertThatThrownBy(() -> Hashtag.of(givenNullTagName))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("Tag name must not be empty")
        );
    }

    @DisplayName("tagname 앞에 # 이 2개 이상 붙을 경우 #을 1개로 줄여서 반환한다.")
    @Test
    void givenTagNameWithMultipleHash_whenValidateTagName_thenReduceHash() {
        // given
        String givenTagName = "##test tag name";

        // when
        Hashtag actual = Hashtag.of(givenTagName);

        // then
        assertThat(actual.getTagName()).isEqualTo("#test tag name");
    }

    @DisplayName("tagname 이 #으로만 이루어졌다면 예외를 반환한다.")
    @Test
    void givenTagNameWithOnlyHash_whenValidateTagName_thenThrowIllegalArgumentException() {
        // given
        String givenTagName = "######";

        // when & then
        assertThatThrownBy(() -> Hashtag.of(givenTagName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Tag name must not be empty");
    }

    @DisplayName("tagname 이 26자를 초과하면 예외를 반환한다.")
    @Test
    void givenTagNameExceeding26Characters_whenValidateTagName_thenThrowIllegalArgumentException() {
        // given
        String givenTagName = "test tag name test tag name test tag name";

        // when & then
        assertThatThrownBy(() -> Hashtag.of(givenTagName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Tag name must not exceed 26 characters");
    }

    @DisplayName("# 이 tagname 중간에 있는건 tagname 의 일부러 판단한다.")
    @Test
    void givenTagNameWithHashInTheMiddle_whenValidateTagName_thenReturnAsItIs() {
        // given
        String givenTagName = "test #tag name";

        // when
        Hashtag actual = Hashtag.of(givenTagName);

        // then
        assertThat(actual.getTagName()).isEqualTo("#" + givenTagName);
    }
}
