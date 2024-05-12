package org.nightdivers.kupica.domain.comment;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByArticleIdAndErasedFlagIsFalse(Long id);

    List<Comment> findAllByMemberIdAndErasedFlagIsFalse(Long id);

    List<Comment> findAllByMemberNicknameAndErasedFlagIsFalse(String nickname);

    List<Comment> findAllByArticleIdAndMemberIdAndErasedFlagIsFalse(Long articleId, Long memberId);

    List<Comment> findAllByArticleIdAndMemberNicknameAndErasedFlagIsFalse(Long id, String nickname);

    List<Comment> findAllByAnonymousUserNicknameAndErasedFlagIsFalse(String nickname);

    List<Comment> findAllByArticleIdAndAnonymousUserNicknameAndErasedFlagIsFalse(Long id, String nickname);
}
