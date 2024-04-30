package org.nightdivers.kupica.domain.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findAllByOrderByCreatedDatetimeDesc(Pageable pageable);

    Page<Article> findAllByOrderByCreatedDatetimeAsc(Pageable pageable);

    Page<Article> findByMemberIdAndLoginFlagIsTrueOrderByCreatedDatetimeDesc(Pageable pageable, Long memberId);

    Page<Article> findByMemberIdAndLoginFlagIsTrueOrderByCreatedDatetimeAsc(Pageable pageable, Long memberId);

    Page<Article> findByAnonymousUserNicknameAndLoginFlagIsFalseOrderByCreatedDatetimeDesc(Pageable pageable, String anonymousUser1);

    Page<Article> findByAnonymousUserNicknameAndLoginFlagIsFalseOrderByCreatedDatetimeAsc(Pageable pageable, String nickname);
}
