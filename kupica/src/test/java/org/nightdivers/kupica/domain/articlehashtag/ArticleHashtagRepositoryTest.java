package org.nightdivers.kupica.domain.articlehashtag;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nightdivers.kupica.support.constant.HashtagConstant.TEST_INVALID_TAG_NAME;
import static org.nightdivers.kupica.support.constant.HashtagConstant.TEST_VALID_TAG_NAME;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
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

@DisplayNameGeneration(ReplaceUnderscores.class)
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
        initTestData();
    }

    private void initTestData() {
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

    @Nested
    class 해시태그와_일치하는_게시글_조회_시 {

        @Nested
        class 존재하는_경우 {

            @Test
            void 해당되는_게시글을_반환한다() {
                List<ArticleHashtag> actual = articleHashtagRepository.findByHashtagTagNameAndErasedFlagIsFalse(
                        PageRequest.of(0, 5), TEST_VALID_TAG_NAME).getContent();

                assertThat(actual).isNotEmpty();
            }
        }

        @Nested
        class 존재하지_않는_경우 {

            @Test
            void 빈_목록을_반환한다() {
                List<ArticleHashtag> articleHashtags = articleHashtagRepository.findByHashtagTagNameAndErasedFlagIsFalse(
                        PageRequest.of(0, 5),
                        TEST_INVALID_TAG_NAME
                ).getContent();

                assertThat(articleHashtags).isEmpty();
            }
        }
    }
}
