package com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigController {

    private static final Logger LOGGER = LogManager.getLogger(ConfigController.class);
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
            LOGGER.info("Successful load properties for class: {}", clazz.getSimpleName());
        } catch (IllegalAccessException | RuntimeException e) {
            LOGGER.warn("Could load program properties from file, reason: {}", e.getMessage(), e);
        }
    }
}
