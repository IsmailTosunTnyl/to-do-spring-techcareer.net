package com.todotechcareer.techcareer.Beans;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2


@Configuration
public class SwaggerConfig {

    // FIRST
    public void swaggerOpenApiBeforeBeanMethod() {
        log.info("swagger Open Api Before Bean başladı");
        System.out.println("swagger Open Api Before Bean başladı");
    }

    // OpenAPI
    @Bean
    public OpenAPI swaggerOpenApiMethod() {
        return new OpenAPI().info(
                new Info()
                        .title("ToDo App API ")
                        .description("ToDo App API Documentation")
                        .version("V1.0")
                        .contact(new Contact()
                                .name("Ismail Tosun")
                                .url("http://ismailtosun.net")
                                .email("ismailtosuntnyl@gmail.com"))
                        .termsOfService(" Software INC.")
                        .license(
                                new License()
                                        .name("Licence ")
                                        .url("www."))
        );
    }

    // LAST
    public void swaggerOpenApiAfterBeanMethod() {
        log.info("swagger Open Api After Bean bitti");
        System.out.println("swagger Open Api After Bean bitti");
    }
} //end class

