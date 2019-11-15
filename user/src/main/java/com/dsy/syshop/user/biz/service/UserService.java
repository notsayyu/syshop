package com.dsy.syshop.user.biz.service;

import com.dsy.syshop.user.biz.model.parm.LoginParam;
import com.dsy.syshop.user.web.vo.UserDetailVO;

/**
 * @description:
 * @author: dsy
 * @date: 2019/11/14 15:52
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param param 登录参数
     * @return 用户详情信息
     */
    UserDetailVO login(LoginParam param);
}
