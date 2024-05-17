package org.nightdivers.kupica.domain.photofilesource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nightdivers.kupica.support.constant.ArticleConstant.TEST_MEMBER_ARTICLE_1_CAPTION;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nightdivers.kupica.domain.anonymoususer.AnonymousUser;
import org.nightdivers.kupica.domain.anonymoususer.AnonymousUserRepository;
import org.nightdivers.kupica.domain.article.Article;
import org.nightdivers.kupica.domain.article.ArticleRepository;
import org.nightdivers.kupica.support.annotation.RepositoryTest;
import org.nightdivers.kupica.support.factory.AnonymousUserFactory;
import org.nightdivers.kupica.support.factory.ArticleFactory;
import org.springframework.data.domain.PageRequest;

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

    /* TARGET : PhotoFileSource 조회 테스트 */
    @DisplayName("article id 와 일치하는 PhotoFileSource 전체 조회 - [성공]")
    @Test
    void givenArticleId_whenFindPhotoFileSourceByArticleId_thenReturnPhotoFileSource() {
        // given

        // when
        List<PhotoFileSource> photoFileSources = photoFileSourceRepository.findByArticleIdAndErasedFlagIsFalse(givenArticle.getId());

        // then
        assertThat(photoFileSources).isNotEmpty();
    }

    @DisplayName("article id 와 일치하는 PhotoFileSource 페이징 조회 - [성공]")
    @Test
    void givenArticleId_whenFindPhotoFileSourceByArticleId_thenReturnPhotoFileSourcePage() {
        // given
        // when
        List<PhotoFileSource> photoFileSources = photoFileSourceRepository.findByArticleIdAndErasedFlagIsFalse(
                PageRequest.of(0, 5), givenArticle.getId()).getContent();

        // then
        assertThat(photoFileSources).isNotEmpty();
    }
}
