package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Configuration {
    private Properties properties = new Properties();
    private static final Logger LOGGER = Logger.getLogger(RoomConfig.class.getSimpleName());

    public Configuration(String path) {
        try (InputStream fileReader = new FileInputStream(path)) {
            properties.load(fileReader);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Could load program properties from file: " + e.toString(), e);
        }
    }

    public boolean checkProperty(String key) {
        if (properties.size() == 0) {
            return false;
        }
        return properties.stringPropertyNames().contains(key);
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
