package org.koreait.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass  //공통 속성 명시
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {  //공통 엔티티 - 추상클래스

    @CreatedDate
    @Column(updatable = false)  //업테이트 불가능(수정 불가능)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)  //수정시에만 수행
    private LocalDateTime modifiedAt;
}
