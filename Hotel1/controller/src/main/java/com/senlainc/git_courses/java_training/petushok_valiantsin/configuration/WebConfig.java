package com.senlainc.git_courses.java_training.petushok_valiantsin.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.senlainc.git_courses.java_training.petushok_valiantsin.controller")
public class WebConfig implements WebMvcConfigurer {

}
