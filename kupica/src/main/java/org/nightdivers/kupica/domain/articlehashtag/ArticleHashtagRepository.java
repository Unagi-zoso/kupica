package org.nightdivers.kupica.domain.articlehashtag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleHashtagRepository extends JpaRepository<ArticleHashtag, Long> {
    Page<ArticleHashtag> findByHashtagTagName(Pageable pageable, String tagName);
}
