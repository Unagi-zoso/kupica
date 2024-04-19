package org.nightdivers.kupica.domain.comment;

import static jakarta.persistence.ConstraintMode.NO_CONSTRAINT;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Getter;
import org.nightdivers.kupica.domain.article.Article;
import org.nightdivers.kupica.domain.member.Member;
import org.nightdivers.kupica.support.domain.ModifiableBaseEntity;

@Getter
@Entity
@Table(name = "comment")
public class Comment extends ModifiableBaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="author_name", nullable = false, length = 18)
    private String authorName;

    @Column(name="content", nullable = false, length = 600)
    private String content;

    @ManyToOne
    @JoinColumn(name="reply_target_comment_id", referencedColumnName = "id", foreignKey = @ForeignKey(NO_CONSTRAINT))
    private Comment replyTargetComment;

    @Column(name="password", length = 64)
    private String password;

    @ManyToOne
    @JoinColumn(name="member_id", referencedColumnName = "id", foreignKey = @ForeignKey(NO_CONSTRAINT))
    private Member member;

    @ManyToOne
    @JoinColumn(name="article_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(NO_CONSTRAINT))
    private Article article;

    protected Comment() {}

    private Comment(String authorName,
                    String content,
                    Comment replyTargetComment,
                    String password,
                    Member member,
                    Article article) {
        this.authorName = authorName;
        this.content = content;
        this.replyTargetComment = replyTargetComment;
        this.password = password;
        this.member = member;
        this.article = article;
    }

    public static Comment of(String authorName,
                             String content,
                             Comment replyTargetComment,
                             String password,
                             Member member,
                             Article article) {
        return new Comment(authorName, content, replyTargetComment, password, member, article);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Comment comment = (Comment) o;

        return this.getId() != null && Objects.equals(getId(), comment.getId());
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hashCode(this.getId());
    }
}
