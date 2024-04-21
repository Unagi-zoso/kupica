package org.nightdivers.kupica.domain.hashtag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Getter;
import org.nightdivers.kupica.support.domain.ModifiableBaseEntity;

@Getter
@Entity
@Table(name = "hashtag")
public class Hashtag extends ModifiableBaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="tag_name", nullable = false, unique = true, length = 26)
    private String tagName;

    protected Hashtag() {}

    private Hashtag(String tagName) {
        this.tagName = tagName;
    }

    public static Hashtag of(String tagName) {
        return new Hashtag(tagName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Hashtag hashtag = (Hashtag) o;

        return this.getId() != null && Objects.equals(this.getId(), hashtag.getId());
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hashCode(this.getId());
    }
}