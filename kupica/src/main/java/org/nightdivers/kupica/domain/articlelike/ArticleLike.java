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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Getter;
import org.nightdivers.kupica.domain.article.Article;
import org.nightdivers.kupica.domain.member.Member;
import org.nightdivers.kupica.support.domain.BaseTimeEntity;

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
@SequenceGenerator(
        name = "article_like_sequence_generator",
        sequenceName = "article_like_sequence",
        allocationSize = 1
)
public class ArticleLike extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_like_sequence_generator")
    @Column(columnDefinition = "NUMERIC(19,0)")
    private Long id;

    @Column(name = "ip_address", length = 20, updatable = false)
    private String ipAddress;

    @ManyToOne
    @JoinColumn(name = "member_id", columnDefinition = "NUMERIC(19,0)", referencedColumnName = "id", foreignKey = @ForeignKey(NO_CONSTRAINT), updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "article_id", columnDefinition = "NUMERIC(19,0)", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(NO_CONSTRAINT), updatable = false)
    private Article article;

    @Column(name = "login_flag", nullable = false, updatable = false)
    private Boolean loginFlag;

    protected ArticleLike() {}

    private ArticleLike(String ipAddress,
                        Member member,
                        Article article,
                        Boolean loginFlag) {
        this.ipAddress = ipAddress;
        this.member = member;
        this.article = article;
        this.loginFlag = loginFlag;
    }

    public static ArticleLike createMemberArticleLike(Member member, Article article) {
        return new ArticleLike(null, member, article, true);
    }

    public static ArticleLike createAnonymousArticleLike(String ipAddress, Article article) {
        return new ArticleLike(ipAddress, null, article, false);
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
