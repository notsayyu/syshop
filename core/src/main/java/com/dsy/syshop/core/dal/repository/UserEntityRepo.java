package com.dsy.syshop.core.dal.repository;

import com.dsy.syshop.core.dal.entity.UserEntity;

import java.util.Optional;

/**
 * @description:
 * @author: dsy
 * @date: 2019/11/14 13:58
 */
public interface UserEntityRepo extends BaseRepo<UserEntity, Integer> {
    Optional<UserEntity> findByUsernameAndDeletedIsFalse(String username);

    Optional<UserEntity> findByMobileAndDeletedIsFalse(String mobile);
}
