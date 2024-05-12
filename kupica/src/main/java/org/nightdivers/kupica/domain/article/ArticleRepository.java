package org.nightdivers.kupica.domain.article;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findByIdAndErasedFlagIsFalse(Long id);

    List<Article> findAllByErasedFlagIsFalse();

    Page<Article> findAllByErasedFlagIsFalseOrderByCreatedDatetimeDesc(Pageable pageable);

    Page<Article> findAllByErasedFlagIsFalseOrderByCreatedDatetimeAsc(Pageable pageable);

    Page<Article> findByMemberIdAndLoginFlagIsTrueAndErasedFlagIsFalseOrderByCreatedDatetimeDesc(
            Pageable pageable,
            Long memberId
    );

    Page<Article> findByMemberIdAndLoginFlagIsTrueAndErasedFlagIsFalseOrderByCreatedDatetimeAsc(
            Pageable pageable,
            Long memberId
    );

    Page<Article> findByAnonymousUserNicknameAndLoginFlagIsFalseAndErasedFlagIsFalseOrderByCreatedDatetimeDesc(
            Pageable pageable,
            String anonymousUser1
    );

    Page<Article> findByAnonymousUserNicknameAndLoginFlagIsFalseAndErasedFlagIsFalseOrderByCreatedDatetimeAsc(
            Pageable pageable,
            String nickname
    );
}
