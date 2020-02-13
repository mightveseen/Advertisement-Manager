package com.senlainc.git_courses.java_training.petushok_valiantsin.configuration;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigController {
    private static ConfigController instance;
    private static final Logger LOGGER = Logger.getLogger(ConfigController.class.getName());

    public static ConfigController getInstance() {
        if (instance == null) {
            instance = new ConfigController();
        }
        return instance;
    }

    public void letsRock(Class<?> clazz) {
        try {
            ConfigService.getInstance().setValue(clazz);
            ConfigService.getInstance().addFieldValue();
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, "Could load program resources.properties from file: " + e.toString(), e);
        }
    }
}
