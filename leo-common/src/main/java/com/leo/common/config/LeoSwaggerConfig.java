package com.leo.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeoSwaggerConfig {

    @Bean
    public OpenAPI springbootOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Leo System ApiDoc")
                        .description("Leo系统接口文档")
                        .termsOfService("https://github.com/wdmh")
                        .contact(new Contact().name("lewis").email("liujie1861@gmail.com"))
                        .version("v1")
                );
    }

    @Bean
    public GroupedOpenApi systemApiGroup() {
        return GroupedOpenApi.builder()
                .group("system")
                .packagesToScan("com.leo.system.controller")
                .build();
    }
}
