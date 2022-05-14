package com.zky.music.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * knife4j接口文档
 */
@Configuration
@EnableSwagger2WebMvc
@EnableKnife4j
@ConfigurationProperties(prefix = "swagger.config")
public class Knife4jConfig {

    @Bean(value = "Zed音乐网站API接口文档")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Zed音乐网站API接口文档")
                        .description("Zed音乐网站系统前后台api接口（Knife4j APIs）")
                        .termsOfServiceUrl("http://localhost:8888/swagger-ui.html")
                        .version("v2.0")
                        .contact(new Contact("yuan","https://github.com/orgs/MJUniversity/repositories","2584278161@qq.com"))
                        .build())
                //分组名称
                .groupName("2.X版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.zky.music.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}