package com.senlainc.git_courses.java_training.petushok_valiantsin.configuration;

import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigProperty;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class ConfigService {
    private final Class<?> configClass;
    private final String configPath;

    public ConfigService(Class<?> clazz) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        this.configClass = clazz;
        final Annotation annotation = configClass.getAnnotation(ConfigClass.class);
        if (annotation == null) {
            throw new IllegalArgumentException("Class didn't have annotation");
        }
        this.configPath = ConfigClass.class.getDeclaredMethod("configPath").invoke(annotation).toString();
    }

    public <T> void addFieldValue(T object) throws IllegalAccessException {
        for (Field field : configClass.getDeclaredFields()) {
            if (field.getAnnotation(ConfigProperty.class) != null) {
                final Config config = new Config(field);
                final Properties properties = ConfigReader.getInstance().readConfig(configPath + config.getConfigName());
                final Object value = customConverter(field, properties.getProperty(config.getPropertyName()));
                config.getField().setAccessible(true);
                config.getField().set(object, value);
                config.getField().setAccessible(false);
            }
        }
    }

    private Object customConverter(Field field, String variable) {
        String variableType = field.getType().toString();
        if (variableType.matches(Byte.class.toString()) || variableType.matches("byte")) {
            return Byte.parseByte(variable);
        }
        if (variableType.matches(Short.class.toString()) || variableType.matches("short")) {
            return Short.parseShort(variable);
        }
        if (variableType.matches(Integer.class.toString()) || variableType.matches("int")) {
            return Integer.parseInt(variable);
        }
        if (variableType.matches(Long.class.toString()) || variableType.matches("long")) {
            return Long.parseLong(variable);
        }
        if (variableType.matches(Float.class.toString()) || variableType.matches("float")) {
            return Float.parseFloat(variable);
        }
        if (variableType.matches(Double.class.toString()) || variableType.matches("double")) {
            return Double.parseDouble(variable);
        }
        if (variableType.matches(Boolean.class.toString()) || variableType.matches("boolean")) {
            return Boolean.parseBoolean(variable);
        }
        if (variableType.matches(Character.class.toString()) || variableType.matches("char")) {
            return variable.charAt(0);
        }
        return variable;
    }
}
