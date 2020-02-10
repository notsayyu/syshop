package com.dsy.syshop.core.biz.service;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: dsy
 * @date: 2020/2/6 12:02
 */
public interface RedisService {
    /**
     * 根据key插入 set集合到redis
     *
     * @param key    key值
     * @param values 集合
     * @return 插入集合大小
     */
    void saveSet(String key, String values);

    /**
     * 删除key值
     *
     * @param key key值
     */
    Boolean delete(String key);


    /**
     * 根据key获取内容
     *
     * @param key
     */
    String getValue(String key);

    /**
     * key存在value加1，若不存在则创建并设置超时时间
     *
     * @param key      key值
     * @param expire   超时时间
     * @param timeUnit 超时时间单位
     * @return 计数
     */
    long increaseOrExpire(String key, long expire, TimeUnit timeUnit);


    /**
     * key存在value加1，若不存在则创建设置为1
     *
     * @param key key值
     * @return 计数
     */
    long increase(String key);

    /**
     * 对key设置相应的实效时间
     *
     * @param key      key值
     * @param expire   超时时间
     * @param timeUnit 超时时间单位
     * @return
     */
    Boolean expire(String key, long expire, TimeUnit timeUnit);

}
