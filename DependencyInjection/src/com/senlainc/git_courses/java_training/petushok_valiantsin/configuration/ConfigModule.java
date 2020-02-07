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

    public<T> void letsRock(String className, T object) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, NoSuchFieldException {
        final Class clazz = Class.forName(className);
        new ConfigInjection(clazz).AddDeclaredFiled(object);
    }
}
