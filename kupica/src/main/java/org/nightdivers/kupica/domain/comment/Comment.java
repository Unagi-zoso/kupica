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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Getter;
import org.nightdivers.kupica.domain.anonymoususer.AnonymousUser;
import org.nightdivers.kupica.domain.article.Article;
import org.nightdivers.kupica.domain.member.Member;
import org.nightdivers.kupica.support.domain.ModifiableBaseEntity;

@Getter
@Entity
@Table(name = "article_comment")
@SequenceGenerator(name = "article_comment_sequence_generator", sequenceName = "article_comment_sequence", allocationSize = 1)
public class Comment extends ModifiableBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_comment_sequence_generator")
    @Column(columnDefinition = "NUMERIC(19,0)")
    private Long id;

    @Column(name = "content", nullable = false, length = 600)
    private String content;

    @ManyToOne
    @JoinColumn(name = "reply_target_comment_id", columnDefinition = "NUMERIC(19,0)", referencedColumnName = "id", foreignKey = @ForeignKey(NO_CONSTRAINT))
    private Comment replyTargetComment;

    @ManyToOne
    @JoinColumn(name = "anonymous_user_id", columnDefinition = "NUMERIC(19,0)", referencedColumnName = "id", foreignKey = @ForeignKey(NO_CONSTRAINT))
    private AnonymousUser anonymousUser;

    @ManyToOne
    @JoinColumn(name = "member_id", columnDefinition = "NUMERIC(19,0)", referencedColumnName = "id", foreignKey = @ForeignKey(NO_CONSTRAINT))
    private Member member;

    @ManyToOne
    @JoinColumn(name = "article_id", columnDefinition = "NUMERIC(19,0)", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(NO_CONSTRAINT))
    private Article article;

    @Column(name = "login_flag", nullable = false)
    private Boolean loginFlag;

    @Column(name = "erased_flag", nullable = false)
    private Boolean erasedFlag;

    protected Comment() {
    }

    private Comment(
            String content,
            Comment replyTargetComment,
            AnonymousUser anonymousUser,
            Member member,
            Article article,
            Boolean loginFlag
    ) {
        this.content = content;
        this.replyTargetComment = replyTargetComment;
        this.anonymousUser = anonymousUser;
        this.member = member;
        this.article = article;
        this.loginFlag = loginFlag;
        this.erasedFlag = false;
    }

    private static Comment of(
            String content,
            Comment replyTargetComment,
            AnonymousUser anonymousUser,
            Member member,
            Article article,
            Boolean loginFlag
    ) {
        return new Comment(content, replyTargetComment, anonymousUser, member, article, loginFlag);
    }

    public static Comment createMemberComment(String content, Member member, Article article) {
        return of(content, null, null, member, article, true);
    }

    public static Comment createAnonymousUserComment(String content, AnonymousUser anonymousUser, Article article) {
        return of(content, null, anonymousUser, null, article, false);
    }

    public static Comment createMemberReplyComment(String content, Comment replyTargetComment, Member member, Article article) {
        return of(content, replyTargetComment, null, member, article, true);
    }

    public static Comment createAnonymousReplyComment(
            String content,
            Comment replyTargetComment,
            AnonymousUser anonymousUser,
            Article article
    ) {
        return of(content, replyTargetComment, anonymousUser, null, article, false);
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

    public void changeContent(String content) {
        this.content = content;
    }

    public void erase() {
        this.erasedFlag = true;
    }
}
