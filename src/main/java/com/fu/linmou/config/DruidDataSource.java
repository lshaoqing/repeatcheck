package com.fu.linmou.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/5/15 17:04
 * @Version: 1.0
 */
//@Configuration
public class DruidDataSource {

    /**
     * 创建 orders 数据源
     */
    @Primary
    @Bean(name = "ordersDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.orders") // 读取 spring.datasource.orders 配置到 HikariDataSource 对象
    public DataSource ordersDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 创建 users 数据源
     */
    @Bean(name = "usersDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.users")
    public DataSource usersDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}
