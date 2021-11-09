package com.senlainc.javacourses.petushokvaliantsin.configuration;

import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.ISingularMapper;
import com.senlainc.javacourses.petushokvaliantsin.utility.mapper.implementation.SingularMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ISingularMapper singularMapper() {
        return new SingularMapper("com.senlainc.javacourses.petushokvaliantsin.service");
    }
}
