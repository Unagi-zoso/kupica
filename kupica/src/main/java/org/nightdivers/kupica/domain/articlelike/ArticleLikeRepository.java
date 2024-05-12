package org.nightdivers.kupica.domain.articlelike;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long> {

    Optional<ArticleLike> findByIdAndErasedFlagIsFalse(Long id);

    List<ArticleLike> findAllByMemberIdAndErasedFlagIsFalse(Long memberId);

    List<ArticleLike> findAllByArticleIdAndErasedFlagIsFalse(Long articleId);

    Long countByArticleIdAndErasedFlagIsFalse(Long articleId);

    @Query("SELECT al FROM ArticleLike al " +
            "WHERE al.erasedFlag = false AND al.id IN ( " +
            "SELECT MIN(al2.id) FROM ArticleLike al2 " +
            "GROUP BY al2.article.id " +
            "ORDER BY COUNT(al2.article.id) DESC" +
            ")"
    )
    Page<ArticleLike> findArticleIdOrderByCountDesc(Pageable pageable);

    @Query("SELECT al FROM ArticleLike al " +
            "WHERE al.erasedFlag = false AND al.id IN ( " +
            "SELECT MIN(al2.id) FROM ArticleLike al2 " +
            "GROUP BY al2.article.id " +
            "ORDER BY COUNT(al2.article.id) ASC" +
            ")"
    )
    Page<ArticleLike> findArticleIdOrderByCountAsc(Pageable pageable);
}
