package com.fu.linmou.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 *   redis配置信息.
 *
 * @author Administrator
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // MyRedisSerializer myRedisSerializer = new MyRedisSerializer();
        // template.setKeySerializer(stringRedisSerializer);
        // template.setValueSerializer(myRedisSerializer);
        // template.setHashKeySerializer(stringRedisSerializer);
        // template.setHashValueSerializer(myRedisSerializer);
        // template.afterPropertiesSet();
        // 使用 String 序列化方式，序列化 KEY 。
        template.setKeySerializer(RedisSerializer.string());
        GenericFastJsonRedisSerializer json = new GenericFastJsonRedisSerializer();
        template.setValueSerializer(json);

        return template;

    }
}
