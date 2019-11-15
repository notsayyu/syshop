package com.dsy.syshop.core.dal.entity;

import com.dsy.syshop.core.common.enums.UserGender;
import com.dsy.syshop.core.common.enums.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @description:
 * @author: dsy
 * @date: 2019/11/14 10:56
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Table(name = "user", indexes = {
        @Index(name = "username_idx", columnList = "username")
})
@Entity
public class UserEntity extends BaseEntity {
    @Column(name = "username", columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '用户名'")
    private String username;

    @Column(name = "password", columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '密码'")
    private String password;

    @Column(name = "gender", columnDefinition = ("varchar(40) not null default 0"))
    @Enumerated(EnumType.STRING)
    private UserGender gender;

    @Column(name = "mobile", columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '手机号'")
    private String mobile;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", columnDefinition = "VARCHAR(45) DEFAULT NULL COMMENT '角色'")
    private UserRole role;
}
