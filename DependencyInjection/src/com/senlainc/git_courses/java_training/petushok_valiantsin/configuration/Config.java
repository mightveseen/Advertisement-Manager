package com.senlainc.git_courses.java_training.petushok_valiantsin.configuration;

import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigProperty;

import java.lang.reflect.Field;

public class Config {
    private final Field field;
    private final String configName;
    private final String propertyName;

    public Config(Field field) {
        this.field = field;
        this.configName = field.getAnnotation(ConfigProperty.class).configName().toLowerCase();
        this.propertyName = setPropertyName();
    }

    private static String splitCamelCase(String name) {
        if (!name.contains("_")) {
            return name.replaceAll(String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])"), "_");
        }
        return name;
    }

    public Field getField() {
        return this.field;
    }

    public String getConfigName() {
        return this.configName;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    private String setPropertyName() {
        if (field.getAnnotation(ConfigProperty.class).propertyName().equals("")) {
            return splitCamelCase(field.getDeclaringClass().getSimpleName()).toUpperCase() + "." + splitCamelCase(field.getName()).toUpperCase();
        }
        return field.getAnnotation(ConfigProperty.class).propertyName();
    }
}
