package com.senlainc.git_courses.java_training.petushok_valiantsin.configuration;

import java.lang.reflect.InvocationTargetException;

public class ConfigModule {
    public static ConfigModule instance;

    public static ConfigModule getInstance() {
        if (instance == null) {
            instance = new ConfigModule();
        }
        return instance;
    }

    public void letsRock(Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        new ConfigInjection(clazz).AddDeclaredFiled();
    }
}
