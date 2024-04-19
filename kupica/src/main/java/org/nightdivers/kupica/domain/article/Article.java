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
import org.nightdivers.kupica.domain.member.Member;
import org.nightdivers.kupica.support.domain.ModifiableBaseEntity;

@Getter
@Entity
@Table(name = "article")
public class Article extends ModifiableBaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="author_name", nullable = false, length = 18)
    private String author_name;

    @Column(name="password", length = 64)
    private String password;

    @Column(name="caption", length = 600)
    private String caption;

    @ManyToOne
    @JoinColumn(name="member_id", referencedColumnName = "id", foreignKey = @ForeignKey(NO_CONSTRAINT))
    private Member member;

    protected Article() {}

    private Article(String author_name,
                    String password,
                    String caption,
                    Member member) {
        this.author_name = author_name;
        this.password = password;
        this.caption = caption;
        this.member = member;
    }

    public static Article of(String author_name,
                             String password,
                             String caption,
                             Member member) {
        return new Article(author_name, password, caption, member);
    }

    public static Article of(String author_name,
                             String password,
                             String caption) {
        return new Article(author_name, password, caption, null);
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
}
