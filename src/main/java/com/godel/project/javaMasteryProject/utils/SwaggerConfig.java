package com.godel.project.javaMasteryProject.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.godel.project.javaMasteryProject"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        return new ApiInfo(
                "Spring boot Swagger documentation",
                "Swagger API",
                "1.0",
                "Terms of service",
                new Contact(
                        "Aliaksandr Linkevich",
                        "https://www.linkedin.com/in/aliaksandr-linkevich-408296200/",
                        "qpuhuk@mail.ru"),
                "My license for example",
                "My license for example",
                Collections.emptyList());
    }
}
