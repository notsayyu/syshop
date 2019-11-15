package com.dsy.syshop.user.web.vo;

import com.dsy.syshop.core.common.enums.UserRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: dsy
 * @date: 2019/11/14 15:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户详细信息")
public class UserDetailVO {
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户手机")
    private String mobile;

    @ApiModelProperty(value = "角色")
    private UserRole role;
}
