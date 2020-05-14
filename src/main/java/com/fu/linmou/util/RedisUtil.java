package com.fu.linmou.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;

/**
 * @author linMou
 * @Description: Redis处理类
 * @Date: 2020/5/14 09:38
 * @Version: 1.0
 */
@Configuration
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public <T>boolean set(final String key,T value) {
        boolean result = false;
        try{
            ValueOperations<Serializable,T> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     *  写入缓存设置过期时间
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public <T>boolean setEx(final String key,T value,long expireTime) {
        boolean result = false;
        try{
            ValueOperations<Serializable,T> operations = redisTemplate.opsForValue();
            operations.set(key, value, expireTime);
            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public<T> T get(final String key) {
        T result = null;
        try{
            ValueOperations<Serializable,T> operations = redisTemplate.opsForValue();
            result = operations.get(key);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除对应的value
     * @param key
     */
    public boolean remove(final String key) {
        if (exists(key)) {
            return redisTemplate.delete(key);
        }
        return false;

    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }
}
