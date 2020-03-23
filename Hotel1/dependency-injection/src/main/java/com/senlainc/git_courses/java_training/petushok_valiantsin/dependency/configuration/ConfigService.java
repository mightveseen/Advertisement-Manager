package com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.configuration;

import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.configuration.annotation.ConfigClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.configuration.annotation.ConfigProperty;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConfigService {

    private static ConfigService instance;
    private Class<?> configClass;
    private String configPath;

    public static ConfigService getInstance() {
        if (instance == null) {
            instance = new ConfigService();
        }
        return instance;
    }

    public void setValue(Class<?> clazz) {
        this.configClass = clazz;
        final ConfigClass annotation = configClass.getAnnotation(ConfigClass.class);
        if (annotation == null) {
            throw new IllegalArgumentException("Class: " + clazz + " didn't have 'ConfigClass' annotation");
        }
        this.configPath = annotation.configPath();
    }

    public void addFieldValue() throws IllegalAccessException {
        final List<Field> annotatedFields = Arrays.stream(configClass.getDeclaredFields())
                .filter(i -> i.isAnnotationPresent(ConfigProperty.class))
                .collect(Collectors.toList());
        for (Field field : annotatedFields) {
            final Config config = new Config(field);
            final Properties properties = ConfigReader.getInstance().readConfig(configPath + config.getConfigName());
            final Object value = customConverter(field.getType(), properties.getProperty(config.getPropertyName()));
            config.getField().setAccessible(true);
            config.getField().set(configClass, value);
            config.getField().setAccessible(false);
        }
    }

    private <T> Object customConverter(Class<T> clazz, String variable) {
        final Function<String, Object> converter;
        switch (clazz.getSimpleName().toLowerCase()) {
            case "byte":
                converter = Byte::valueOf;
                break;
            case "short":
                converter = Short::valueOf;
                break;
            case "int":
            case "integer":
                converter = Integer::valueOf;
                break;
            case "long":
                converter = Long::valueOf;
                break;
            case "float":
                converter = Float::valueOf;
                break;
            case "double":
                converter = Double::valueOf;
                break;
            case "boolean":
                converter = Boolean::valueOf;
                break;
            case "char":
            case "character":
                return variable.charAt(0);
            default:
                return variable;
        }
        return converter.apply(variable);
    }
}
