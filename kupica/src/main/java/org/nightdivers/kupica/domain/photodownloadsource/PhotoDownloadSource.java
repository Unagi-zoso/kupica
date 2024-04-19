package org.nightdivers.kupica.domain.photodownloadsource;

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
import org.nightdivers.kupica.support.domain.ModifiableBaseEntity;

@Getter
@Entity
@Table(name = "photo_download_source")
public class PhotoDownloadSource  extends ModifiableBaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="photo_resolution", nullable = false, length = 18)
    private String photoResolution;

    @Column(name="file_source", nullable = false, unique = true, length = 600)
    private String fileSource;

    @Column(name="file_byte_size", nullable = false)
    private Long fileByteSize;

    @Column(name="download_count", nullable = false)
    private Long downloadCount;

    @ManyToOne
    @JoinColumn(name="article_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(NO_CONSTRAINT))
    private Article article;

    protected PhotoDownloadSource() {}

    private PhotoDownloadSource(String photoResolution,
                                String fileSource,
                                Long fileByteSize,
                                Long downloadCount,
                                Article article) {
        this.photoResolution = photoResolution;
        this.fileSource = fileSource;
        this.fileByteSize = fileByteSize;
        this.downloadCount = downloadCount;
        this.article = article;
    }

    public static PhotoDownloadSource of(
            String photoResolution,
            String fileSource,
            Long fileByteSize,
            Long downloadCount,
            Article article) {
        return new PhotoDownloadSource(photoResolution, fileSource, fileByteSize, downloadCount, article);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PhotoDownloadSource photoDownloadSource = (PhotoDownloadSource) o;

        return this.getId() != null && Objects.equals(this.getId(), photoDownloadSource.getId());
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hashCode(this.getId());
    }
}
