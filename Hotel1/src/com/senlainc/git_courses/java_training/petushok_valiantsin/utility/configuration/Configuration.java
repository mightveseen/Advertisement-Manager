package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Configuration {
    private static final Logger LOGGER = Logger.getLogger(Configuration.class.getName());
    private final Properties properties = new Properties();

    protected Configuration(String path) {
        try (InputStream fileReader = new FileInputStream(path)) {
            properties.load(fileReader);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Could load program properties from file: " + e.toString(), e);
        }
    }

    protected boolean checkProperty(String key) {
        return properties.containsKey(key);
    }

    protected String getValue(String key) {
        return properties.getProperty(key);
    }
}
