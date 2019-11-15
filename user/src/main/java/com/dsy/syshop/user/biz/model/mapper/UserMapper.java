package com.dsy.syshop.user.biz.model.mapper;

import com.dsy.syshop.core.dal.entity.UserEntity;
import com.dsy.syshop.user.web.vo.UserDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: dsy
 * @date: 2019/11/14 23:38
 */
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDetailVO toUserDetailVO(UserEntity userEntity);

}
