package com.senlainc.git_courses.java_training.petushok_valiantsin.configuration;

import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigClass;

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
            final Config config = new Config(field);
            final Properties properties = ConfigReader.getInstance().readConfig(configPath + config.getConfigName());
            final Object value = customConverter(config, properties.getProperty(config.getPropertyName()));
            config.getField().setAccessible(true);
            config.getField().set(object, value);
            config.getField().setAccessible(false);
        }
    }

    private Object customConverter(Config config, String variable) {
        if (Byte.class.equals(config.getField().getType())) {
            return Byte.parseByte(variable);
        }
        if (Short.class.equals(config.getField().getType())) {
            return Short.parseShort(variable);
        }
        if (Integer.class.equals(config.getField().getType())) {
            return Integer.parseInt(variable);
        }
        if (Long.class.equals(config.getField().getType())) {
            return Long.parseLong(variable);
        }
        if (Float.class.equals(config.getField().getType())) {
            return Float.parseFloat(variable);
        }
        if (Double.class.equals(config.getField().getType())) {
            return Double.parseDouble(variable);
        }
        if (Boolean.class.equals(config.getField().getType())) {
            return Boolean.parseBoolean(variable);
        }
        if (Character.class.equals(config.getField().getType())) {
            return variable.charAt(0);
        }
        return variable;
    }
}
