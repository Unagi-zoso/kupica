package org.nightdivers.kupica.support.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@ToString
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseTimeEntity {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(name = "created_datetime", nullable = false, updatable = false)
    protected LocalDateTime createdDatetime;
}
