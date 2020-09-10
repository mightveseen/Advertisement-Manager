package com.senlainc.javacourses.petushokvaliantsin.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(StarterProperties.class)
public class StarterConfiguration {

    private final StarterProperties properties;

    @Autowired
    public StarterConfiguration(StarterProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnProperty("custom.starter.firstProperty")
    public StarterBean starterBean() {
        return new StarterBean(properties.getFirstProperty(), properties.getSecondProperty());
    }
}
