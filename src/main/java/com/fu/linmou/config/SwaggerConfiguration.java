package com.fu.linmou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author linMou
 * @Description: 由于Spring Boot 暂未提供 Swagger 内置的支持，所以我们需要自己定义配置类。
 * @Date: 2020/5/14 14:21
 * @Version: 1.0
 */
@Configuration
@EnableSwagger2 // 标记项目启用 Swagger API 接口文档
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)  // 文档类型，使用 Swagger2
                .apiInfo(this.apiInfo()) // 设置 API 信息
                // 扫描 Controller 包路径，获得 API 接口
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fu.linmou.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建 API 信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .description("接口文档")
                .version("1.0")
                //联系人
                .contact(new Contact("hongsen", "http://www.hongsen.com", "linshaoqing@hsv-tec.com"))
                .build();
    }

}
