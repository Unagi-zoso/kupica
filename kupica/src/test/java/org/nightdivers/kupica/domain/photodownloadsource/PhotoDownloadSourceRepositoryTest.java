package org.nightdivers.kupica.domain.photodownloadsource;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nightdivers.kupica.support.annotation.RepositoryTest;
import org.springframework.data.domain.PageRequest;

@RequiredArgsConstructor
@RepositoryTest
class PhotoDownloadSourceRepositoryTest {
    private final PhotoDownloadSourceRepository photoDownloadSourceRepository;

    /* PhotoDownloadSource 조회 */
    @DisplayName("article id 와 일치하는 PhotoDownloadSource 전체 조회 - [성공]")
    @Test
    void givenArticleId_whenFindPhotoDownloadSourceByArticleId_thenReturnPhotoDownloadSource() {
        // given

        // when
        List<PhotoDownloadSource> photoDownloadSources = photoDownloadSourceRepository.findByArticleId(1L);

        // then
        assertThat(photoDownloadSources).isNotEmpty();
    }

    @DisplayName("article id 와 일치하는 PhotoDownloadSource 페이징 조회 - [성공]")
    @Test
    void givenArticleId_whenFindPhotoDownloadSourceByArticleId_thenReturnPhotoDownloadSourcePage() {
        // given

        // when
        List<PhotoDownloadSource> photoDownloadSources = photoDownloadSourceRepository.findByArticleId(PageRequest.of(0, 5), 1L).getContent();

        // then
        assertThat(photoDownloadSources).isNotEmpty();
    }

}
