package org.nightdivers.kupica.domain.articlelike;

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
@Table(
        name = "article_like",
        uniqueConstraints = {
                @jakarta.persistence.UniqueConstraint(
                        name = "article_like_member_id_article_id_unique",
                        columnNames = {"ip_address", "member_id", "article_id"}
                )
        }
)
public class ArticleLike extends ModifiableBaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="ip_address", length = 20)
    private String ipAddress;

    @ManyToOne
    @JoinColumn(name="member_id", referencedColumnName = "id", foreignKey = @ForeignKey(NO_CONSTRAINT))
    private Member member;

    @ManyToOne
    @JoinColumn(name="article_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(NO_CONSTRAINT))
    private Article article;

    protected ArticleLike() {}

    private ArticleLike(String ipAddress,
                        Member member,
                        Article article) {
        this.ipAddress = ipAddress;
        this.member = member;
        this.article = article;
    }

    public static ArticleLike of(String ipAddress,
                                 Member member,
                                 Article article) {
        return new ArticleLike(ipAddress, member, article);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ArticleLike articleLike = (ArticleLike) o;

        return this.getId() != null && Objects.equals(this.getId(), articleLike.getId());
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hashCode(this.getId());
    }
}
