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

    public void letsRock(Class<?> object) {
        try {
            ConfigService.getInstance().setValue(object);
            ConfigService.getInstance().addFieldValue();
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
