package com.senlainc.javacourses.petushokvaliantsin.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(StarterProperties.class)
public class StarterConfiguration {

    private StarterProperties properties;

    @Autowired
    public StarterConfiguration(StarterProperties properties) {
        this.properties = properties;
    }

    @Bean
    public StarterBean starterBean() {
        return new StarterBean(properties.getFirstProperty(), properties.getSecondProperty());
    }
}
