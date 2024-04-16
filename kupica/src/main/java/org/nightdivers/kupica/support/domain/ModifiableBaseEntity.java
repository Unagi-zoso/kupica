package org.nightdivers.kupica.support.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@ToString
@MappedSuperclass
public abstract class ModifiableBaseEntity extends BaseTimeEntity {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(name = "updated_datetime", nullable = false)
    protected String updatedDatetime;
}
