package org.nightdivers.kupica.domain.hashtag;

import static org.nightdivers.kupica.domain.hashtag.HashtagValidator.returnValidTagName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Getter;
import org.nightdivers.kupica.support.domain.ModifiableBaseEntity;

@Getter
@Entity
@Table(name = "hashtag")
@SequenceGenerator(name = "hashtag_sequence_generator", sequenceName = "hashtag_sequence", allocationSize = 1)
public class Hashtag extends ModifiableBaseEntity {

    public final static int MAX_TAG_NAME_WITH_HASH_LENGTH = 26;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hashtag_sequence_generator")
    @Column(columnDefinition = "NUMERIC(19,0)")
    private Long id;

    @Column(name = "tag_name", nullable = false, unique = true, length = MAX_TAG_NAME_WITH_HASH_LENGTH)
    private String tagName;

    protected Hashtag() {
    }

    private Hashtag(String tagName) {
        this.tagName = returnValidTagName(tagName);
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

    public void changeTagName(String tagName) {
        this.tagName = returnValidTagName(tagName);
    }
}
