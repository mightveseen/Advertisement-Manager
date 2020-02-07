package com.senlainc.git_courses.java_training.petushok_valiantsin.configuration;

import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class ConfigInjection {
    private final Class<?> configClass;
    private final String configPath;

    public ConfigInjection(Class<?> clazz) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        this.configClass = clazz;
        final Annotation annotation = configClass.getAnnotation(ConfigClass.class);
        if (annotation == null) {
            throw new IllegalArgumentException("Class didn't have annotation");
        }
        this.configPath = ConfigClass.class.getDeclaredMethod("configPath").invoke(annotation).toString();
    }

    public <T> void AddDeclaredFiled(T object) throws IllegalAccessException {
        for (Field field : configClass.getDeclaredFields()) {
            final ConfigModel configModel = new ConfigModel(field);
            final Properties properties = ConfigReader.getInstance().readConfig(configPath + configModel.getConfigName());
            final Object value = customConverter(configModel, properties.getProperty(configModel.getPropertyName()));
            configModel.getField().setAccessible(true);
            configModel.getField().set(object, value);
            configModel.getField().setAccessible(false);
        }
    }

    private Object customConverter(ConfigModel configModel, String variable) {
        if (Byte.class.equals(configModel.getField().getType())) {
            return Byte.parseByte(variable);
        }
        if (Short.class.equals(configModel.getField().getType())) {
            return Short.parseShort(variable);
        }
        if (Integer.class.equals(configModel.getField().getType())) {
            return Integer.parseInt(variable);
        }
        if (Long.class.equals(configModel.getField().getType())) {
            return Long.parseLong(variable);
        }
        if (Float.class.equals(configModel.getField().getType())) {
            return Float.parseFloat(variable);
        }
        if (Double.class.equals(configModel.getField().getType())) {
            return Double.parseDouble(variable);
        }
        if (Boolean.class.equals(configModel.getField().getType())) {
            return Boolean.parseBoolean(variable);
        }
        if (Character.class.equals(configModel.getField().getType())) {
            return variable.charAt(0);
        }
        return variable;
    }
}
