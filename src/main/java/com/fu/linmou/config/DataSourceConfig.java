package com.fu.linmou.config;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/5/15 15:43
 * @Version: 1.0
 */
//@Configuration
public class DataSourceConfig {

    /**
     * 创建 orders 数据源的配置对象
     */
    @Primary
    @Bean(name = "orderDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.orders")  //读取 spring.datasource.orders 配置到 DataSourceProperties 对象
    public DataSourceProperties orderDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 创建orders数据源
     */
    @Bean(name = "orderDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.orders.hikari") // 读取 spring.datasource.orders 配置到 HikariDataSource 对象
    public DataSource orderDataSource() {
        DataSourceProperties properties = this.orderDataSourceProperties();
        return createHikariDataSource(properties);
    }

    /**
     * 创建 users 数据源的配置对象
     */
    @Bean(name = "usersDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.users")  //读取 spring.datasource.users 配置到 DataSourceProperties 对象
    public DataSourceProperties usersDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 创建users数据源
     */
    @Bean(name = "usersDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.users.hikari") // 读取 spring.datasource.users 配置到 HikariDataSource 对象
    public DataSource usersDataSource() {
        DataSourceProperties properties = this.usersDataSourceProperties();
        return createHikariDataSource(properties);
    }

    private DataSource createHikariDataSource(DataSourceProperties properties) {
        HikariDataSource dataSource = properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        //// 设置线程池名
        if(StringUtils.hasText(properties.getName())) {
            dataSource.setPoolName(properties.getName());
        }
        return dataSource;
    }

}
