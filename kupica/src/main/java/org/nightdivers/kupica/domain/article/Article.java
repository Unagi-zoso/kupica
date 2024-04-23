package org.nightdivers.kupica.domain.article;

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
import org.nightdivers.kupica.domain.anonymoususer.AnonymousUser;
import org.nightdivers.kupica.domain.member.Member;
import org.nightdivers.kupica.support.domain.ModifiableBaseEntity;

@Getter
@Entity
@Table(name = "article")
public class Article extends ModifiableBaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="caption", length = 600, nullable = false)
    private String caption;

    @ManyToOne
    @JoinColumn(name="anonymous_user_id", referencedColumnName = "id", foreignKey = @ForeignKey(NO_CONSTRAINT), updatable = false)
    private AnonymousUser anonymousUser;

    @ManyToOne
    @JoinColumn(name="member_id", referencedColumnName = "id", foreignKey = @ForeignKey(NO_CONSTRAINT), updatable = false)
    private Member member;

    @Column(name="login_flag", nullable = false, columnDefinition = "TINYINT(1)", updatable = false)
    private Boolean loginFlag;

    protected Article() {}

    private Article(String caption,
                    Member member,
                    AnonymousUser anonymousUser,
                    Boolean loginFlag) {
        this.caption = caption;
        this.member = member;
        this.anonymousUser = anonymousUser;
        this.loginFlag = loginFlag;
    }

    private static Article of(String caption,
                             Member member,
                             AnonymousUser anonymousUser,
                             Boolean loginFlag) {
        return new Article(caption, member, anonymousUser, loginFlag);
    }

    public static Article createMemberArticle(String caption, Member member) {
        return of(caption, member, null, true);
    }

    public static Article createAnonymousArticle(String caption, AnonymousUser anonymousUser) {
        return of(caption, null, anonymousUser, false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Article article = (Article) o;

        return this.getId() != null && Objects.equals(this.getId(), article.getId());
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hashCode(this.getId());
    }

    public void changeCaption(String caption) {
        this.caption = caption;
    }
}
