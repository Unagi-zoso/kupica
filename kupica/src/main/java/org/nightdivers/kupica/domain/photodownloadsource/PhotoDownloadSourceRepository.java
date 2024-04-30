package org.nightdivers.kupica.domain.photodownloadsource;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoDownloadSourceRepository extends JpaRepository<PhotoDownloadSource, Long> {
    List<PhotoDownloadSource> findByArticleId(long articleId);
    
    Page<PhotoDownloadSource> findByArticleId(Pageable pageable, long articleId);
}
