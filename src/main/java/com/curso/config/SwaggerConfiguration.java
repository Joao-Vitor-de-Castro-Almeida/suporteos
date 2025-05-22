package com.curso.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("SuporteOs2025")
                .pathsToMatch("/**")
                .packagesToScan("com.curso.resources")
                .build();
    }

    @Bean
    public OpenAPI customAPI(){
        return new OpenAPI().info(new Info().title("Suporte OS 2025")
                                            .description("Suporte OS 2025")
                                            .version("1.0")
                                            .contact(new Contact().name("Curso Spring")
                                                                  .url("https://github.com/curso-spring/Curso-Spring")
                                                                  .email("curso@curso.com.br")));
    }

}
