package com.dsy.syshop.core.biz.service.impl;

import com.dsy.syshop.core.biz.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: dsy
 * @date: 2020/2/10 11:38
 */
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {
    private final StringRedisTemplate redisTemplate;

    public RedisServiceImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 根据key插入 set集合到redis
     *
     * @param key    key值
     * @param values 集合
     * @return 插入集合大小
     */
    @Override
    public void saveSet(String key, String values) {
        log.debug("RedisUtils saveSet key:{}", key);
        redisTemplate.opsForValue().set(key, values);
    }

    /**
     * 删除key值
     *
     * @param key key值
     */
    @Override
    public Boolean delete(String key) {
        log.debug("RedisUtils delete key:{}", key);
        return redisTemplate.opsForSet().getOperations().delete(key);
    }

    /**
     * 根据key获取内容
     *
     * @param key
     */
    @Override
    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * key存在value加1，若不存在则创建并设置超时时间
     *
     * @param key      key值
     * @param expire   超时时间
     * @param timeUnit 超时时间单位
     * @return 计数
     */
    @Override
    public long increaseOrExpire(String key, long expire, TimeUnit timeUnit) {
        String cnt = getValue(key);

        if (cnt != null) {
            log.debug("key:{} cnt:{} increment ", key, cnt);
            return redisTemplate.opsForValue().increment(key);
        } else {
            redisTemplate.opsForValue().set(key, "1");
            redisTemplate.expire(key, expire, timeUnit);
            log.debug("create rateLimiter expire:{} - {} - {} ", key, expire, timeUnit);
            return 1;
        }
    }

    /**
     * key存在value加1，若不存在则创建设置为1
     *
     * @param key key值
     * @return 计数
     */
    @Override
    public long increase(String key) {
        String cnt = getValue(key);
        if (cnt != null) {
            log.debug("key:{} cnt:{} increment in redis. ", key, cnt);
            return redisTemplate.opsForValue().increment(key);
        } else {
            redisTemplate.opsForValue().set(key, "1");
            log.debug("create key:{} in redis.", key);
            return 1;
        }
    }

    /**
     * 对key设置相应的实效时间
     *
     * @param key      key值
     * @param expire   超时时间
     * @param timeUnit 超时时间单位
     * @return
     */
    @Override
    public Boolean expire(String key, long expire, TimeUnit timeUnit) {
        String value = getValue(key);
        if (!Strings.isBlank(value)) {
            return redisTemplate.expire(key, expire, timeUnit);
        }
        return false;
    }
}
