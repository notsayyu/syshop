package com.dsy.syshop.user.common.configuration.security;

import com.dsy.syshop.core.common.enums.UserRole;
import com.dsy.syshop.core.dal.entity.UserEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @description:
 * @author: dsy
 * @date: 2019/11/13 19:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Scope("session")
public class UserDetail implements UserDetails {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "是否禁用")
    private Boolean deleted;

    @ApiModelProperty(value = "用户角色")
    private UserRole role;

    public static UserDetail parse(UserEntity userEntity) {
        return new UserDetail(userEntity.getId(), userEntity.getUsername(),
                userEntity.getMobile(), userEntity.isDeleted(), userEntity.getRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(role.name());
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !deleted;
    }
}
