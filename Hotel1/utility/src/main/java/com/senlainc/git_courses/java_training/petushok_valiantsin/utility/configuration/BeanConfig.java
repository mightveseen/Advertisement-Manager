package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.senlainc.git_courses.java_training.petushok_valiantsin.repository",
        "com.senlainc.git_courses.java_training.petushok_valiantsin.service",
        "com.senlainc.git_courses.java_training.petushok_valiantsin.controller"})
public class BeanConfig {

}
