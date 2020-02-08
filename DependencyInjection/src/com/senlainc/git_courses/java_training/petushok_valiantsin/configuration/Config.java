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

    public Field getField() {
        return this.field;
    }

    public String getConfigName() {
        return this.configName;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    private static String splitCamelCase(String name) {
        return name.replaceAll(String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])"), "_");
    }

    private String setPropertyName() {
        if (field.getAnnotation(ConfigProperty.class).propertyName().equals("")) {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(splitCamelCase(field.getDeclaringClass().getSimpleName()).toUpperCase()).append(".");
            if(!field.getName().contains("_")) {
                return stringBuilder.append(splitCamelCase(field.getName()).toUpperCase()).toString();
            }
            return stringBuilder.append(field.getName().toUpperCase()).toString();
        }
        return field.getAnnotation(ConfigProperty.class).propertyName();
    }
}
