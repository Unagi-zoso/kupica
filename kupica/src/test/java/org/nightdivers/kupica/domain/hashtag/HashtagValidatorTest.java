package org.nightdivers.kupica.domain.hashtag;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.nightdivers.kupica.domain.hashtag.Hashtag.MAX_TAG_NAME_WITH_HASH_LENGTH;
import static org.nightdivers.kupica.domain.hashtag.HashtagValidator.returnValidTagName;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
class HashtagValidatorTest {

    @Nested
    class 태그_이름_유효성_검사_시 {

        @Nested
        class 태그_이름_앞에_해시태그가_없는_경우 {

            String givenTagName = "test tag name";

            @Test
            void 해시태그를_추가하여_반환한다() {
                String actual = returnValidTagName(givenTagName);

                assertThat(actual).isEqualTo("#" + givenTagName);
            }
        }

        @Nested
        class 태그_이름_앞에_해시태그가_붙은_경우 {

            String givenTagName = "#test tag name";

            @Test
            void 그대로_반환한다() {
                String actual = returnValidTagName(givenTagName);

                assertThat(actual).isEqualTo(givenTagName);
            }
        }

        @Nested
        class 태그_이름_앞에_해시태그가_2개_이상_붙은_경우 {

            String givenTagName = "##test tag name";

            @Test
            void 해시태그를_1개로_줄여서_반환한다() {
                String actual = returnValidTagName(givenTagName);

                assertThat(actual).isEqualTo("#test tag name");
            }
        }

        @Nested
        class 태그_이름이_빈_문자열인_경우 {

            String givenEmptyTagName = "";
            String givenNullTagName = null;

            @Test
            void IllegalArgumentException_을_던진다() {
                assertAll(
                        () -> assertThatThrownBy(() -> returnValidTagName(givenEmptyTagName))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessage("태그 이름은 비어있을 수 없습니다."),
                        () -> assertThatThrownBy(() -> returnValidTagName(givenNullTagName))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessage("태그 이름은 비어있을 수 없습니다.")
                );
            }
        }

        @Nested
        class 태그_이름이_해시태그로만_이루어진_경우 {

            String givenTagName = "######";

            @Test
            void IllegalArgumentException_을_던진다() {
                assertThatThrownBy(() -> returnValidTagName(givenTagName))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("태그 이름은 최소 2글자 이상이어야 합니다.");
            }
        }
        
        @Nested
        class 태그_이름이_26자를_초과하는_경우 {

            String givenTagName = "test tag name test tag name test tag name";

            @Test
            void IllegalArgumentException_을_던진다() {
                assertThatThrownBy(() -> returnValidTagName(givenTagName))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("태그 이름은 " + MAX_TAG_NAME_WITH_HASH_LENGTH + " 을 넘을 수 없습니다.");
            }
        }

        @Nested
        class 태그_이름_중간에_해시태그가_있는_경우 {

            String givenTagName = "#test #tag name";

            @Test
            void 해시태그를_태그_이름의_일부로_판단하고_그대로_반환한다() {
                String actual = returnValidTagName(givenTagName);

                assertThat(actual).isEqualTo(givenTagName);
            }
        }
    }
}
