package org.nightdivers.kupica.domain.articlehashtag;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nightdivers.kupica.support.constant.HashtagConstant.TEST_INVALID_TAG_NAME;
import static org.nightdivers.kupica.support.constant.HashtagConstant.TEST_VALID_TAG_NAME;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nightdivers.kupica.support.annotation.RepositoryTest;
import org.springframework.data.domain.PageRequest;

@RequiredArgsConstructor
@RepositoryTest
class ArticleHashtagRepositoryTest {

    private final ArticleHashtagRepository articleHashtagRepository;

    /* TARGET : ArticleHashtag 조회 테스트 */
    @DisplayName("Hashtag 와 일치하는 Article 페이지로 조회 - [성공]")
    @Test
    void givenHashtag_whenFindArticleByHashtag_thenReturnArticlePage() {
        // given

        // when
        List<ArticleHashtag> articleHashtags = articleHashtagRepository.findByHashtagTagNameAndErasedFlagIsFalse(
                PageRequest.of(0, 5), TEST_VALID_TAG_NAME).getContent();

        // then
        assertThat(articleHashtags).isNotEmpty();
    }

    @DisplayName("Hashtag 와 일치하는 Article 페이지로 조회 - [실패 : Hashtag 미존재]")
    @Test
    void givenHashtag_whenFindArticleByHashtag_thenEmpty() {
        // given

        // when
        List<ArticleHashtag> articleHashtags = articleHashtagRepository.findByHashtagTagNameAndErasedFlagIsFalse(
                PageRequest.of(0, 5),
                TEST_INVALID_TAG_NAME
        ).getContent();

        // then
        assertThat(articleHashtags).isEmpty();
    }
}
