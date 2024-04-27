package org.nightdivers.kupica.domain.article;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByCreatedDatetimeDesc();

    List<Article> findAllByOrderByCreatedDatetimeAsc();

    List<Article> findByMemberIdAndLoginFlagIsTrueOrderByCreatedDatetimeDesc(Long memberId);

    List<Article> findByMemberIdAndLoginFlagIsTrueOrderByCreatedDatetimeAsc(Long memberId);

    List<Article> findByAnonymousUserNicknameAndLoginFlagIsFalseOrderByCreatedDatetimeDesc(String anonymousUser1);

    List<Article> findByAnonymousUserNicknameAndLoginFlagIsFalseOrderByCreatedDatetimeAsc(String nickname);
}
