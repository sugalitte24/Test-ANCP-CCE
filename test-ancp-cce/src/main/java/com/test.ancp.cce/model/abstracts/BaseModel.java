package com.test.ancp.cce.model.abstracts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public abstract class BaseModel {

    @Id
    public final UUID uuid = UUID.randomUUID();

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    public LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "last_modified_at")
    @LastModifiedDate
    public LocalDateTime lastModifiedAt = LocalDateTime.now();
}
