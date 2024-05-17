package org.nightdivers.kupica.domain.articlehashtag;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nightdivers.kupica.support.constant.HashtagConstant.TEST_INVALID_TAG_NAME;
import static org.nightdivers.kupica.support.constant.HashtagConstant.TEST_VALID_TAG_NAME;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nightdivers.kupica.domain.anonymoususer.AnonymousUser;
import org.nightdivers.kupica.domain.anonymoususer.AnonymousUserRepository;
import org.nightdivers.kupica.domain.article.Article;
import org.nightdivers.kupica.domain.article.ArticleRepository;
import org.nightdivers.kupica.domain.hashtag.Hashtag;
import org.nightdivers.kupica.domain.hashtag.HashtagRepository;
import org.nightdivers.kupica.support.annotation.RepositoryTest;
import org.nightdivers.kupica.support.factory.AnonymousUserFactory;
import org.nightdivers.kupica.support.factory.ArticleFactory;
import org.springframework.data.domain.PageRequest;

@RequiredArgsConstructor
@RepositoryTest
class ArticleHashtagRepositoryTest {

    private final ArticleRepository articleRepository;

    private final AnonymousUserRepository anonymousUserRepository;

    private final HashtagRepository hashtagRepository;

    private final ArticleHashtagRepository articleHashtagRepository;

    AnonymousUser givenAnonymousUser;

    List<Article> givenArticles;

    Hashtag givenHashtag;

    List<ArticleHashtag> givenArticleHashtags;

    @BeforeEach
    void setUp() {
        givenAnonymousUser = anonymousUserRepository.save(AnonymousUserFactory.createTestAnonymousUser1());
        givenArticles = articleRepository.saveAll(
                List.of(
                        ArticleFactory.createTestAnonymousArticle1(givenAnonymousUser),
                        ArticleFactory.createTestAnonymousArticle1(givenAnonymousUser),
                        ArticleFactory.createTestAnonymousArticle1(givenAnonymousUser)
                )
        );
        givenHashtag = hashtagRepository.save(Hashtag.of(TEST_VALID_TAG_NAME));
        givenArticleHashtags = articleHashtagRepository.saveAll(
                List.of(
                        ArticleHashtag.of(givenArticles.get(0), givenHashtag),
                        ArticleHashtag.of(givenArticles.get(1), givenHashtag),
                        ArticleHashtag.of(givenArticles.get(2), givenHashtag)
                )
        );
    }

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
