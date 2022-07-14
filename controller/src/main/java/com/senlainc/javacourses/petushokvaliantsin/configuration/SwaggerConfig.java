package com.senlainc.javacourses.petushokvaliantsin.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static java.lang.String.format;

@Slf4j
@Configuration
@NoArgsConstructor
public class SwaggerConfig {

    @Value("${project.version:}")
    private String version;

    @Value("${service.name:AuditGet}")
    private String serviceName;

    @Value("#{${swagger.custom.contacts:{null:null}}}")
    private Map<String, String> contacts;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title(format("Microservice `%s`", serviceName))
                .description("The service provides a universal audit interface for operations performed within " +
                        "SmartVistaServices, as well as data on changes recorded in the Mobile Client service event log.")
                .version(version)
                .contact(contacts.entrySet().stream()
                        .findFirst().map(entity -> new Contact().name(entity.getKey()).url(entity.getValue()))
                        .orElse(null)
                )
        );
    }
}
