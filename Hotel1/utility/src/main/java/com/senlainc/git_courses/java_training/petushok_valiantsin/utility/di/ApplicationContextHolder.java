package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextHolder {

    private static ApplicationContext applicationContext;

    private ApplicationContextHolder() {
        throw new IllegalStateException("Utility class");
    }

    public static void loadConfig() {
        if (applicationContext == null) {
            applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        }
    }

    public static Object getBean(Class<?> clazz) {
        return applicationContext.getBean(clazz);
    }
}
