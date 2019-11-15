package com.dsy.syshop.core.common.constant;

/**
 * @description:
 * @author: dsy
 * @date: 2019/11/14 15:32
 */
public class RegexPattern {
    /**
     * 中国境内手机号码正则表达式
     * See Also: https://github.com/VincentSit/ChinaMobilePhoneNumberRegex
     */
    public final static String MOBILE_ALL = "^(?=\\d{11}$)^1(?:3\\d|4[57]|5[^4\\D]|66|7[^249\\D]|8\\d|9[89])\\d{8}$";

    /**
     * 密码规则,8-16位，字母\数字以及特殊字符组成的混合
     */
    public final static String PASSWORD_RULE = "^((?=.*\\d)(?=.*\\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))(?!^.*[\\u4E00-\\u9FA5].*$)^\\S{8,16}$";


}
