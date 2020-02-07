package com.senlainc.git_courses.java_training.petushok_valiantsin.configuration;

import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigProperty;

import java.lang.reflect.Field;

public class ConfigModel {
    private final Field filed;
    private final String configName;
    private final String propertyName;

    public ConfigModel(Field field) {
        this.filed = field;
        this.configName = field.getAnnotation(ConfigProperty.class).configName();
        this.propertyName = field.getAnnotation(ConfigProperty.class).propertyName();
    }

    public Field getFiled() {
        return this.filed;
    }

    public String getConfigName() {
        return this.configName;
    }

    public String getPropertyName() {
        return this.propertyName;
    }
}
