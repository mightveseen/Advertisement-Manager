package com.senlainc.git_courses.java_training.petushok_valiantsin.configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigController {
    private static final Logger LOGGER = Logger.getLogger(ConfigController.class.getName());
    private static ConfigController instance;

    public static ConfigController getInstance() {
        if (instance == null) {
            instance = new ConfigController();
        }
        return instance;
    }

    public void setConfig(Class<?> clazz) {
        try {
            ConfigService.getInstance().setValue(clazz);
            ConfigService.getInstance().addFieldValue();
        } catch (IllegalAccessException e) {
            LOGGER.log(Level.WARNING, String.format("Could load program resources.properties from file: %s", e.toString()), e);
        }
    }
}
