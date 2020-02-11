package com.senlainc.git_courses.java_training.petushok_valiantsin.configuration;

import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigProperty;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
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

    public void setValue(Class<?> clazz) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        this.configClass = clazz;
        final Annotation annotation = configClass.getAnnotation(ConfigClass.class);
        if (clazz.isAnnotation()) {
            throw new IllegalArgumentException("Class didn't have annotation");
        }
        this.configPath = ConfigClass.class.getDeclaredMethod("configPath").invoke(annotation).toString();
    }

    public void addFieldValue() throws IllegalAccessException, ClassNotFoundException {
        final List<Field> annotatedFields = Arrays.stream(configClass.getDeclaredFields()).filter(i -> i.isAnnotationPresent(ConfigProperty.class)).collect(Collectors.toList());
        for (Field field : annotatedFields) {
            final Config config = new Config(field);
            final Properties properties = ConfigReader.getInstance().readConfig(configPath + config.getConfigName());
            final Object value = customConverter(field, properties.getProperty(config.getPropertyName()));
            config.getField().setAccessible(true);
            config.getField().set(configClass, value);
            config.getField().setAccessible(false);
        }
    }

    private Object customConverter(Field field, String variable) throws ClassNotFoundException {
        final String variableType = field.getType().toString();
//        Class<?> clazz = Class.forName(field.getType().getName());
//        System.out.println(field.getClass().cast(clazz));
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
