package io.github.wotjd243.findbyhint.util;
/*
 *
 * @author DoYoung
 *
 */

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class DateTimeEntity implements Serializable {

    @CreatedDate
    @Convert(converter = LocalDateTimePersistenceConverter.class)  // <- @Converter를 지정 해줘야 한다.
    @Column(name ="created_at",updatable = false)
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    @Convert(converter = LocalDateTimePersistenceConverter.class)  // <- @Converter를 지정 해줘야 한다.
    @Column(name ="modified_at",updatable = true)
    private LocalDateTime modifiedDateTime;

}