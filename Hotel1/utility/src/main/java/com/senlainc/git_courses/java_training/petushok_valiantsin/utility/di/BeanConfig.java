package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.di;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.senlainc.git_courses.java_training.petushok_valiantsin.repository",
        "com.senlainc.git_courses.java_training.petushok_valiantsin.service",
        "com.senlainc.git_courses.java_training.petushok_valiantsin.controller",
        "com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.room"})
public class BeanConfig {

}
