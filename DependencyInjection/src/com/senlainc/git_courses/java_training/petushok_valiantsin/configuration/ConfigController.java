package com.senlainc.git_courses.java_training.petushok_valiantsin.configuration;

import java.lang.reflect.InvocationTargetException;

public class ConfigController {
    private static ConfigController instance;

    public static ConfigController getInstance() {
        if (instance == null) {
            instance = new ConfigController();
        }
        return instance;
    }

    public <T> void letsRock(T object) throws RuntimeException {
        try {
            final Class<?> clazz = Class.forName(object.getClass().getName());
            new ConfigService(clazz).addFieldValue(object);
        } catch (InvocationTargetException | NoSuchMethodException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}
