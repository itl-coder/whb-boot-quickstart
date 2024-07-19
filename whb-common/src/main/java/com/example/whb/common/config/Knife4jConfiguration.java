package com.example.whb.common.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Slf4j
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {
    @Value("${swagger.title}")
    private String title;
    @Value("${swagger.enabled}")
    private Boolean enabled;

    @Bean
    public Docket adminApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 是否启用Swagger
                .enable(enabled)
                .groupName("adminApi")
                .apiInfo(adminApiInfo())
                .select()
                .paths(PathSelectors.regex("/admin/.*"))
                .build();
    }

    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enabled)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(PathSelectors.regex("/api/.*"))
                .build();
    }

    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description("本文档描述了后台管理系统的各个模块的接口的调用方式")
                .version("1.6")
                .contact(new Contact("", "", ""))
                .build();
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("网站API文档")
                .description("本文档描述了网站各个模块的接口的调用方式")
                .version("1.6")
                .contact(new Contact("", "", ""))
                .build();
    }
}