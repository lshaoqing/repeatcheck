package com.fu.linmou;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
@Slf4j
public class LinmouApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LinmouApplication.class, args);
    }

    @Resource(name = "ordersDataSource")
    private DataSource ordersDataSource;

    @Resource(name = "usersDataSource")
    private DataSource usersDataSource;
    // @Resource(name = "orderDataSource")
    // DataSource orderDataSource;
    //
    // @Resource(name = "usersDataSource")
    // DataSource usersDataSource;

    @Override
    public void run(String... args) throws Exception {
        // orders 数据源
        log.info("[run][获得数据源：{}]", ordersDataSource.getClass());

        // users 数据源
        log.info("[run][获得数据源：{}]", usersDataSource.getClass());

    }

}
