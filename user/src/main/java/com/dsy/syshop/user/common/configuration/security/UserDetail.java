package com.dsy.syshop.user.common.configuration.security;

import com.dsy.syshop.core.common.enums.UserRole;
import com.dsy.syshop.core.dal.entity.UserEntity;
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

    private Integer id;

    private String userName;

    private String mobile;

    private Boolean deleted;

    private UserRole role;

    public static UserDetail parse(UserEntity userEntity){
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
