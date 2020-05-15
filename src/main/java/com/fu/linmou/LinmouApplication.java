package com.fu.linmou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class LinmouApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinmouApplication.class, args);
    }

}
