package org.nightdivers.kupica.domain.photofilesource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nightdivers.kupica.support.constant.ArticleConstant.TEST_MEMBER_ARTICLE_1_CAPTION;

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
import org.nightdivers.kupica.support.annotation.RepositoryTest;
import org.nightdivers.kupica.support.factory.AnonymousUserFactory;
import org.nightdivers.kupica.support.factory.ArticleFactory;
import org.springframework.data.domain.PageRequest;

@DisplayNameGeneration(ReplaceUnderscores.class)
@RequiredArgsConstructor
@RepositoryTest
class PhotoFileSourceRepositoryTest {

    private final AnonymousUserRepository anonymousUserRepository;
    private final ArticleRepository articleRepository;
    private final PhotoFileSourceRepository photoFileSourceRepository;

    AnonymousUser givenAnonymousUser;
    Article givenArticle;
    List<PhotoFileSource> givenPhotoFileSources;

    @BeforeEach
    void setUp() {
        initTestData();
    }

    @Nested
    class 전체_PhotoFileSource_를_조회할_시 {

        @Nested
        class article_id_와_일치하는_PhotoFileSource_가_있는_경우 {

            @Test
            void 해당되는_페이징_결과를_반환한다() {
                List<PhotoFileSource> photoFileSources = photoFileSourceRepository.findByArticleIdAndErasedFlagIsFalse(
                        PageRequest.of(0, 5), givenArticle.getId()).getContent();

                assertThat(photoFileSources).isNotEmpty();
            }
        }

        @Nested
        class article_id_와_일치하는_PhotoFileSource_가_없는_경우 {

            @Test
            void 빈_결과를_반환한다() {
                List<PhotoFileSource> photoFileSources = photoFileSourceRepository.findByArticleIdAndErasedFlagIsFalse(
                        PageRequest.of(0, 5), 0L).getContent();

                assertThat(photoFileSources).isEmpty();
            }
        }
    }

    private void initTestData() {
        givenAnonymousUser = anonymousUserRepository.save(AnonymousUserFactory.createTestAnonymousUser1());

        givenArticle = articleRepository.save(
                ArticleFactory.createCustomAnonymousArticle(TEST_MEMBER_ARTICLE_1_CAPTION, givenAnonymousUser));

        givenPhotoFileSources = photoFileSourceRepository.saveAll(
                List.of(

                        PhotoFileSource.of("840x640", "test1.com", 1000L, 5L, givenArticle),
                        PhotoFileSource.of("840x640", "test2.com", 1000L, 5L, givenArticle)
                )
        );
    }
}
