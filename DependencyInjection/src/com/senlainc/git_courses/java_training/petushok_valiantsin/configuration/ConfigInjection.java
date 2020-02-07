package com.senlainc.git_courses.java_training.petushok_valiantsin.configuration;

import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class ConfigInjection {
    private final Class configClass;
    private String configPath;

    public ConfigInjection(Class configClass) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        this.configClass = configClass;
        final Annotation annotation = this.configClass.getAnnotation(ConfigClass.class);
        if (annotation == null) {
            throw new IllegalArgumentException("Class didn't have annotation");
        }
        this.configPath = ConfigClass.class.getDeclaredMethod("configPath").invoke(annotation).toString();
    }

    public<T> void AddDeclaredFiled(T clazz) throws IllegalAccessException, NoSuchFieldException {
        for (Field field : configClass.getDeclaredFields()) {
            final ConfigModel configModel = new ConfigModel(field);
            final Properties properties = ConfigReader.getInstance().readConfig(configPath + configModel.getConfigName());
            configModel.getFiled().setAccessible(true);
            configModel.getFiled().set(clazz, properties.getProperty(configModel.getPropertyName()));
            configModel.getFiled().setAccessible(false);
        }
    }
}
