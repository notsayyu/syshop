package com.dsy.syshop.core.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.time.Instant;

/**
 * @description:
 * @author: dsy
 * @date: 2019/11/13 19:33
 */
@Data
@MappedSuperclass
@OptimisticLocking
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 数据库版本信息，Hibernate自动维护,乐观锁
     */
    @Version
    public Integer version;

    @Column(name = "created_at", columnDefinition = "BIGINT COMMENT '创建时间'")
    public long createdAt;

    @Column(name = "updated_at", columnDefinition = "BIGINT COMMENT '更新时间'")
    public long updatedAt;

    @Column(name = "deleted", columnDefinition = "BOOLEAN DEFAULT false COMMENT '是否逻辑删除'")
    private boolean deleted;

    @PrePersist
    public void beforeInsert() {
        this.createdAt = Instant.now().toEpochMilli();
        this.updatedAt = Instant.now().toEpochMilli();
    }

    @PreUpdate
    public void beforeUpdate() {
        this.updatedAt = Instant.now().toEpochMilli();
    }
}
