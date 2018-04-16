package com.zhengrongkan.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: ryan
 * @Description: 访问地址：http://localhost:8080/swagger-ui.html
 * @Date: Created in 下午17:28 2018/4/16
 * @Modified By:
 */
@Configuration
@EnableSwagger2
public class Swagger {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zhengrongkan.api.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Jarslink Demo Api ")
                .description("SpringMVC + Mybatis")
                .termsOfServiceUrl("")
                .contact("zhengrongkan")
                .version("1.0")
                .build();
    }
}
