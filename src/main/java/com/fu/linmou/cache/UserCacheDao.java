package com.fu.linmou.cache;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/5/15 14:07
 * @Version: 1.0
 */
@Repository
public class UserCacheDao {

    private final static String KEY_PATTERN = "user:%d";

    @Resource(name = "redisTemplate")
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private ValueOperations<String, String> operations;

    private static String buildKey(Integer id) {
        return String.format(KEY_PATTERN,id);
    }

    public UserCacheObject get(Integer id) {
        String key = buildKey(id);
        String value = operations.get(key);
       return JSON.parseObject(value,UserCacheObject.class);
    }

    public void set(Integer id,UserCacheObject object) {
        String key = buildKey(id);
        String value = JSON.toJSONString(object);
        operations.set(key,value);
    }
}
