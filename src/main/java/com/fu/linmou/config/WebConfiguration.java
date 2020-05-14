package com.fu.linmou.config;

import com.fu.linmou.interceptor.AutoIdempotentInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author linMou
 * @Description: web配置类  bean config 注入
 * @Date: 2020/5/14 10:40
 * @Version: 1.0
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    public AutoIdempotentInterceptor autoIdempotentInterceptor() {
       return new AutoIdempotentInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(autoIdempotentInterceptor());
    }
}
