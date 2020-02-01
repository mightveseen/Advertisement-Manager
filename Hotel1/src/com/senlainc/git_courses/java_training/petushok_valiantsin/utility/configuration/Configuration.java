package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Configuration {
    private Properties properties = new Properties();
    private static final Logger LOGGER = Logger.getLogger(RoomConfig.class.getSimpleName());

    public Configuration(String... propertyName) {
        readProperties(setPath());
        try {
            if (!checkProperty(propertyName)) {
                throw new RuntimeException("Property didn't exists");
            }
        } catch (RuntimeException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    private void readProperties(String path) {
        try (InputStream fileReader = new FileInputStream(path)) {
            properties.load(fileReader);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Could load program properties from file: " + e.toString(), e);
        }
    }

    public boolean checkProperty(String... propertyName) {
        if (properties.size() == 0) {
            return false;
        }
        Set<String> readProperties = properties.stringPropertyNames();
        Set<String> gottenProperties = new HashSet<>(Arrays.asList(propertyName));
        return readProperties.containsAll(gottenProperties);
    }

    public String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }

    private String setPath() {
        return "src/com/senlainc/git_courses/java_training/petushok_valiantsin/utility/configuration/program.properties";
    }
}
