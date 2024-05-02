package org.nightdivers.kupica.domain.comment;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByArticleId(Long id);

    List<Comment> findAllByMemberId(Long id);

    List<Comment> findAllByMemberNickname(String nickname);

    List<Comment> findAllByArticleIdAndMemberId(Long articleId, Long memberId);

    List<Comment> findAllByArticleIdAndMemberNickname(Long id, String nickname);

    List<Comment> findAllByAnonymousUserNickname(String nickname);

    List<Comment> findAllByArticleIdAndAnonymousUserNickname(Long id, String nickname);
}
