package com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.configuration;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigController {
    private static final Logger LOGGER = LogManager.getLogger(ConfigController.class.getName());
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
            LOGGER.log(Level.DEBUG, String.format("Successful load properties for class: %s", clazz.getSimpleName()));
        } catch (IllegalAccessException e) {
            LOGGER.log(Level.WARN, String.format("Could load program resources.properties from file: %s", e.toString()), e);
        }
    }
}
