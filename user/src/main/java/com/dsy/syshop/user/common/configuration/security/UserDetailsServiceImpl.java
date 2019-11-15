package com.dsy.syshop.user.common.configuration.security;

import com.dsy.syshop.core.common.enums.Code;
import com.dsy.syshop.core.common.exception.BusinessException;
import com.dsy.syshop.core.dal.entity.UserEntity;
import com.dsy.syshop.core.dal.repository.UserEntityRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: dsy
 * @date: 2019/11/14 13:58
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserEntityRepo userEntityRepo;

    public UserDetailsServiceImpl(UserEntityRepo userEntityRepo) {
        this.userEntityRepo = userEntityRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityRepo.findByUsernameAndDeletedIsFalse(username).orElseThrow(() -> new BusinessException(Code.ACCOUNT_NOT_EXIST));
        UserDetail userDetail = UserDetail.parse(userEntity);
        return userDetail;
    }
}
