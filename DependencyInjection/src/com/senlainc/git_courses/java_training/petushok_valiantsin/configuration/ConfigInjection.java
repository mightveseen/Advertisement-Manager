package com.senlainc.git_courses.java_training.petushok_valiantsin.configuration;

import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConfigInjection {
    private final Class configClass;
    private String configPath;

    public ConfigInjection(Class configClass) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException{
        this.configClass = configClass;
        Annotation annotation = this.configClass.getAnnotation(ConfigClass.class);
        if (annotation == null) {
            throw new IllegalArgumentException("Class didn't have annotation");
        }
        Method classPathMethod = ConfigClass.class.getDeclaredMethod("configPath");
        this.configPath = classPathMethod.invoke(annotation).toString();
    }

    public void AddDeclaredFiled() throws IllegalAccessException{
        for (Field field : configClass.getDeclaredFields()) {
            ConfigModel configModel = new ConfigModel(field);
            ConfigReader.getInstance().readConfig(configPath, configModel);
        }
    }
}
