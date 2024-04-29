package org.nightdivers.kupica.domain.articlelike;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long> {
    List<ArticleLike> findAllByMemberId(Long memberId);

    List<ArticleLike> findAllByArticleId(Long articleId);
}
