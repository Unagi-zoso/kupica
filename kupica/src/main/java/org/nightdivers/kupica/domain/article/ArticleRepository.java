package org.nightdivers.kupica.domain.article;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByCreatedDatetimeDesc();

    List<Article> findAllByOrderByCreatedDatetimeAsc();

    List<Article> findByMemberIdAndLoginFlagIsTrueOrderByCreatedDatetimeDesc(Long memberId);

    List<Article> findByMemberIdAndLoginFlagIsTrueOrderByCreatedDatetimeAsc(Long memberId);

    List<Article> findByAnonymousUserNicknameAndLoginFlagIsFalseOrderByCreatedDatetimeDesc(String anonymousUser1);

    List<Article> findByAnonymousUserNicknameAndLoginFlagIsFalseOrderByCreatedDatetimeAsc(String nickname);

    @Query(value =
            "SELECT a FROM Article a "
                    + "LEFT JOIN a.member m "
                    + "LEFT JOIN ArticleLike al ON al.article = a "
                    + "GROUP BY a ORDER BY COUNT(al) DESC"
    )
    Page<Article> findArticlesOrderByLikesCountDesc(Pageable pageable);

    @Query(value =
            "SELECT a FROM Article a "
                    + "LEFT JOIN a.member m "
                    + "LEFT JOIN ArticleLike al ON al.article = a "
                    + "GROUP BY a ORDER BY COUNT(al) ASC"
    )
    Page<Article> findArticlesOrderByLikesCountAsc(Pageable pageable);
}
