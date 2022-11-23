package com.project.blogapi.blogapi.config;




import java.util.Collections;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;

import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public Docket api() {
        
        return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(getInfo())
        .select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();

    }
    private ApiInfo getInfo() {
        return new ApiInfo("Bloggging Application: Backend Course", "This Project is developed by Anushka","1.0","Terms of Services", new Contact("Anushka", "htttps://learncodewithdugesh", "anushkapragati99@gmail.com"),"License of APIS", "API liense URl",Collections.emptyList());
    };
}
