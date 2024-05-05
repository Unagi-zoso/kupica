package org.nightdivers.kupica.domain.photofilesource;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoFileSourceRepository extends JpaRepository<PhotoFileSource, Long> {

    List<PhotoFileSource> findByArticleId(long articleId);

    Page<PhotoFileSource> findByArticleId(Pageable pageable, long articleId);
}
