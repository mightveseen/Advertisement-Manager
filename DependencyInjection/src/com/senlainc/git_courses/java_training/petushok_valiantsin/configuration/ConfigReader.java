package com.senlainc.git_courses.java_training.petushok_valiantsin.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static ConfigReader instance;
    private static String mainPath;
    private final Properties properties = new Properties();

    public static ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    public Properties readConfig(String path) {
        if (!path.equals(mainPath)) {
            mainPath = path;
            try (InputStream fileReader = ConfigReader.class.getResourceAsStream(mainPath + ".properties")) {
                properties.load(fileReader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return properties;
    }
}
