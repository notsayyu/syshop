package com.dsy.syshop.user.biz.service.impl;

import com.dsy.syshop.core.common.enums.Code;
import com.dsy.syshop.core.common.enums.UserGender;
import com.dsy.syshop.core.common.enums.UserRole;
import com.dsy.syshop.core.common.exception.BusinessException;
import com.dsy.syshop.core.dal.entity.UserEntity;
import com.dsy.syshop.core.dal.repository.UserEntityRepo;
import com.dsy.syshop.user.biz.model.mapper.UserMapper;
import com.dsy.syshop.user.biz.model.parm.LoginParam;
import com.dsy.syshop.user.biz.service.UserService;
import com.dsy.syshop.user.common.configuration.security.SecurityConfig;
import com.dsy.syshop.user.common.configuration.security.UserDetail;
import com.dsy.syshop.user.web.vo.UserDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: dsy
 * @date: 2019/11/14 16:21
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserEntityRepo userEntityRepo;

    public UserServiceImpl(UserEntityRepo userEntityRepo) {
        this.userEntityRepo = userEntityRepo;
    }

    @Override
    public UserDetailVO login(LoginParam param) {
        //TODO 待加入重试登录次数限制（redis）
        UserEntity userEntity = userEntityRepo.findByMobileAndDeletedIsFalse(param.getMobile())
                .orElse(null);
        //用户不存在则自动为其创建，为输入的密码
        if (userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setMobile(param.getMobile());
            userEntity.setGender(UserGender.UNKNOWN);
            userEntity.setPassword(BCrypt.hashpw(param.getPassword(), BCrypt.gensalt()));
            userEntity.setRole(UserRole.USER);
            //TODO 缺少用户code、实名认证情况（暂不加）
            userEntityRepo.save(userEntity);
            log.info("user:{} registered siyushop system", param.getMobile());
        }
        if (userEntity.isDeleted()) {
            throw new BusinessException(Code.ACCOUNT_NOT_EXIST);
        }
        if (!BCrypt.checkpw(param.getPassword(), userEntity.getPassword())) {
            throw new BusinessException(Code.BAD_CREDENTIALS);
        }
        SecurityConfig.addSecurityContent(UserDetail.parse(userEntity));
        log.info("username:{}, mobile:{}, role:{} login success", userEntity.getUsername(), userEntity.getMobile(), userEntity.getRole());
        return UserMapper.INSTANCE.toUserDetailVO(userEntity);
    }
}
