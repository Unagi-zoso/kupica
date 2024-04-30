package org.nightdivers.kupica.domain.hashtag;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.nightdivers.kupica.support.constant.HashtagConstant.TEST_INVALID_HASHTAG_ID;
import static org.nightdivers.kupica.support.constant.HashtagConstant.TEST_TAG_NAME_1;
import static org.nightdivers.kupica.support.constant.HashtagConstant.TEST_TAG_NAME_2;
import static org.nightdivers.kupica.support.constant.HashtagConstant.TEST_TAG_NAME_3;
import static org.nightdivers.kupica.support.constant.HashtagConstant.TEST_TAG_NAME_4;

import jakarta.persistence.EntityManager;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nightdivers.kupica.domain.article.Article;
import org.nightdivers.kupica.support.annotation.RepositoryTest;
import org.nightdivers.kupica.support.factory.ArticleFactory;
import org.springframework.dao.DataIntegrityViolationException;

@RequiredArgsConstructor
@RepositoryTest
class HashtagRepositoryTest {
    private final HashtagRepository hashtagRepository;
    private final EntityManager entityManager;

    Article givenArticle;

    Hashtag givenHashtag1;
    Hashtag givenHashtag2;
    Hashtag givenHashtag3;

    @BeforeEach
    void setUp() {
        givenArticle = ArticleFactory.createTestAnonymousArticle1();

        givenHashtag1 = hashtagRepository.save(Hashtag.of(TEST_TAG_NAME_1));
        givenHashtag2 = hashtagRepository.save(Hashtag.of(TEST_TAG_NAME_2));
        givenHashtag3 = hashtagRepository.save(Hashtag.of(TEST_TAG_NAME_3));
    }

    /* Hashtag 조회 */
    @DisplayName("해시태그 조회 - [성공]")
    @Test
    void givenHashtag_whenFindById_thenSuccess() {
        // given

        // when
        Hashtag actualHashtag = hashtagRepository.findById(givenHashtag1.getId()).orElseThrow(NoSuchElementException::new);

        // then
        assertThat(actualHashtag).isEqualTo(givenHashtag1);
    }

    @DisplayName("해시태그 조회 - [실패 : 존재하지 않는 ID]")
    @Test
    void givenInvalidHashtagId_whenFindById_thenFail() {
        // given

        // when & then
        assertThatThrownBy(() -> hashtagRepository.findById(TEST_INVALID_HASHTAG_ID).orElseThrow(NoSuchElementException::new))
                .isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("해시태그 전체 조회 - [성공]")
    @Test
    void givenHashtags_whenFindAll_thenSuccess() {
        // given

        // when
        Iterable<Hashtag> actualHashtags = hashtagRepository.findAll();

        // then
        assertThat(actualHashtags).contains(givenHashtag1, givenHashtag2, givenHashtag3);
    }


    /* Hashtag 생성 */
    @DisplayName("해시태그 생성 - [성공]")
    @Test
    void givenHashtag_whenSave_thenSuccess() {
        // given
        Hashtag expectedHashtag = Hashtag.of(TEST_TAG_NAME_4);

        // when
        Hashtag actualHashtag = hashtagRepository.save(expectedHashtag);

        // then
        assertThat(actualHashtag).isEqualTo(expectedHashtag);
    }

    @DisplayName("해시태그 생성 - [실패 : 중복된 태그명]")
    @Test
    void givenHashtag_whenSave_thenThrowDataIntegrityViolationException() {
        // given
        Hashtag expectedHashtag = Hashtag.of(TEST_TAG_NAME_1);

        // when & then
        assertThatThrownBy(() -> hashtagRepository.save(expectedHashtag))
                .isInstanceOf(DataIntegrityViolationException.class);
    }


    /* Hashtag 수정 */
    @DisplayName("해시태그 수정 - [성공]")
    @Test
    void givenHashtag_whenUpdate_thenSuccess() {
        // given

        // when
        givenHashtag1.changeTagName(TEST_TAG_NAME_4);
        Hashtag actualHashtag = hashtagRepository.findById(givenHashtag1.getId()).orElseThrow(NoSuchElementException::new);

        // then
        assertThat(actualHashtag.getTagName()).isEqualTo(TEST_TAG_NAME_4);
    }

    @DisplayName("해시태그 수정 - [실패 : 중복된 태그명]")
    @Test
    void givenHashtag_whenUpdate_thenThrowConstraintViolationException() {
        // given
        givenHashtag1.changeTagName(TEST_TAG_NAME_2);

        // when & then
        assertThatThrownBy(entityManager::flush)
                .isInstanceOf(ConstraintViolationException.class);
    }


    /* Hashtag 삭제 */
    @DisplayName("해시태그 삭제 - [성공]")
    @Test
    void givenHashtag_whenDelete_thenSuccess() {
        // given

        // when
        hashtagRepository.deleteById(givenHashtag1.getId());

        // then
        assertThat(hashtagRepository.findAll()).doesNotContain(givenHashtag1);
    }
}
