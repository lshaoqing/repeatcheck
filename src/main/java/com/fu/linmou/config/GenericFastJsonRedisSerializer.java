package com.fu.linmou.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.IOUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @author linMou
 * @Description: 自定义序列化
 * @Date: 2020/5/15 13:56
 * @Version: 1.0
 */
public class GenericFastJsonRedisSerializer implements RedisSerializer<Object> {

    private final static ParserConfig defaultRedisConfig = new ParserConfig();
    static {
        defaultRedisConfig.setAutoTypeSupport(true);
    }

    @Override
    public byte[] serialize(Object object) throws SerializationException {
        if(object == null) {
            return new byte[0];
        }
        try{
            return JSON.toJSONBytes(object, SerializerFeature.WriteClassName);
        }catch (Exception ex) {
            throw new SerializationException("Could not serialize: " + ex.getMessage(), ex);
        }

    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if(bytes == null || bytes.length == 0) {
            return null;
        }

        try{
            return JSON.parseObject(new String(bytes, IOUtils.UTF8),Object.class,defaultRedisConfig);
        }catch (Exception ex) {
            throw new SerializationException("Could not deserialize: " + ex.getMessage(), ex);

        }
    }

    @Override
    public boolean canSerialize(Class<?> type) {
        return false;
    }

    @Override
    public Class<?> getTargetType() {
        return null;
    }
}
