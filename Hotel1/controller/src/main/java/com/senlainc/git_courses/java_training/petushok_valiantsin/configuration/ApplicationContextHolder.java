package com.senlainc.git_courses.java_training.petushok_valiantsin.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class ApplicationContextHolder {

    private static ApplicationContext applicationContext;

    private ApplicationContextHolder() {
        throw new IllegalStateException("Utility class");
    }

    public static void loadConfig() {
        if (applicationContext == null) {
            applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
