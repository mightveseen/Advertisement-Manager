package com.senlainc.javacourses.petushokvaliantsin.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.senlainc.javacourses.petushokvaliantsin.controller")
public class WebConfig implements WebMvcConfigurer {

}
