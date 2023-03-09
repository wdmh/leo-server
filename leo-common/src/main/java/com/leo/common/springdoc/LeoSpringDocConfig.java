package com.leo.common.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.DateTimeSchema;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;

@Configuration
public class LeoSpringDocConfig {

    static {
        SpringDocUtils.getConfig().replaceWithSchema(LocalTime.class, new DateTimeSchema());
    }

    @Bean
    public OpenAPI springbootOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Leo系统接口文档")
                        .description("Leo系统接口文档")
                        .contact(new Contact().name("lewis").email("liujie1861@gmail.com"))
                        .version("v1")
                );
    }
}
