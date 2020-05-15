package com.fu.linmou.service;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.Serializable;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/5/15 13:24
 * @Version: 1.0
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void Test01() {
        stringRedisTemplate.opsForValue().set("ali","yuanma");
    }

    @Test
    void  Test02() {
        redisTemplate.opsForValue().set("yunai", "shuai");
    }

    @Test
    public void testStringSetKeyUserCache() {
        UserCacheObject object = new UserCacheObject();
        object.setId(1);

        object.setName("芋道源码");
        object.setGender(1); // 男
        String key = String.format("user:%d", object.getId());
        redisTemplate.opsForValue().set(key, object);
    }

    @Test
    public void testStringGetKeyUserCache() {
        String key = String.format("user:%d", 1);
        Object value = redisTemplate.opsForValue().get(key);
        System.out.println(value);
    }

}
