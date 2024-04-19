package org.nightdivers.kupica.domain.articlehashtag;

import static jakarta.persistence.ConstraintMode.NO_CONSTRAINT;

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
import org.nightdivers.kupica.domain.hashtag.Hashtag;
import org.nightdivers.kupica.support.domain.ModifiableBaseEntity;

@Getter
@Entity
@Table(name = "article_hashtag")
public class ArticleHashtag extends ModifiableBaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="article_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(NO_CONSTRAINT))
    private Article article;

    @ManyToOne
    @JoinColumn(name="hashtag_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(NO_CONSTRAINT))
    private Hashtag hashtag;

    protected ArticleHashtag() {}

    private ArticleHashtag(Article article,
                           Hashtag hashtag) {
        this.article = article;
        this.hashtag = hashtag;
    }

    public static ArticleHashtag of(Article article,
                                    Hashtag hashtag) {
        return new ArticleHashtag(article, hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ArticleHashtag articleHashtag = (ArticleHashtag) o;

        return this.getId() != null && Objects.equals(this.getId(), articleHashtag.getId());
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hashCode(this.getId());
    }
}
