package org.nightdivers.kupica.domain.photofilesource;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nightdivers.kupica.support.annotation.RepositoryTest;
import org.springframework.data.domain.PageRequest;

@RequiredArgsConstructor
@RepositoryTest
class PhotoFileSourceRepositoryTest {

    private final PhotoFileSourceRepository photoFileSourceRepository;

    /* PhotoFileSource 조회 */
    @DisplayName("article id 와 일치하는 PhotoFileSource 전체 조회 - [성공]")
    @Test
    void givenArticleId_whenFindPhotoFileSourceByArticleId_thenReturnPhotoFileSource() {
        // given

        // when
        List<PhotoFileSource> photoFileSources = photoFileSourceRepository.findByArticleId(
                1L);

        // then
        assertThat(photoFileSources).isNotEmpty();
    }

    @DisplayName("article id 와 일치하는 PhotoFileSource 페이징 조회 - [성공]")
    @Test
    void givenArticleId_whenFindPhotoFileSourceByArticleId_thenReturnPhotoFileSourcePage() {
        // given

        // when
        List<PhotoFileSource> photoFileSources = photoFileSourceRepository.findByArticleId(
                PageRequest.of(0, 5), 1L).getContent();

        // then
        assertThat(photoFileSources).isNotEmpty();
    }

}
