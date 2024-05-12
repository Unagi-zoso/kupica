package org.nightdivers.kupica.domain.photofilesource;

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
import org.nightdivers.kupica.support.domain.ModifiableBaseEntity;

@Getter
@Entity
@Table(name = "photo_file_source")
@SequenceGenerator(name = "photo_file_source_sequence_generator", sequenceName = "photo_file_source_sequence", allocationSize = 1)
public class PhotoFileSource extends ModifiableBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photo_file_source_sequence_generator")
    @Column(columnDefinition = "NUMERIC(19,0)")
    private Long id;

    @Column(name = "photo_resolution", nullable = false, length = 18)
    private String photoResolution;

    @Column(name = "file_source", nullable = false, unique = true, length = 600)
    private String fileSource;

    @Column(name = "file_byte_size", columnDefinition = "NUMERIC(19,0)", nullable = false)
    private Long fileByteSize;

    @Column(name = "download_count", columnDefinition = "NUMERIC(19,0)", nullable = false)
    private Long downloadCount;

    @ManyToOne
    @JoinColumn(name = "article_id", columnDefinition = "NUMERIC(19,0)", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(NO_CONSTRAINT))
    private Article article;

    @Column(name = "erased_flag", nullable = false)
    private Boolean erasedFlag;

    protected PhotoFileSource() {
    }

    private PhotoFileSource(
            String photoResolution,
            String fileSource,
            Long fileByteSize,
            Long downloadCount,
            Article article
    ) {
        this.photoResolution = photoResolution;
        this.fileSource = fileSource;
        this.fileByteSize = fileByteSize;
        this.downloadCount = downloadCount;
        this.article = article;
        this.erasedFlag = false;
    }

    public static PhotoFileSource of(
            String photoResolution,
            String fileSource,
            Long fileByteSize,
            Long downloadCount,
            Article article
    ) {
        return new PhotoFileSource(photoResolution, fileSource, fileByteSize, downloadCount, article);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PhotoFileSource photoFileSource = (PhotoFileSource) o;

        return this.getId() != null && Objects.equals(this.getId(), photoFileSource.getId());
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hashCode(this.getId());
    }

    public void erase() {
        this.erasedFlag = true;
    }
}
