package com.senlainc.git_courses.java_training.petushok_valiantsin.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    public static ConfigReader instance;
    private static String mainPath;
    private final Properties properties = new Properties();

    public static ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    public void readConfig(String path, ConfigModel configModel) throws IllegalAccessException {
        if (!path.equals(mainPath)) {
            mainPath = path;
            try (InputStream fileReader = new FileInputStream(mainPath + configModel.getConfigName() + ".properties")) {
                properties.load(fileReader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        configModel.getFiled().setAccessible(true);
        configModel.getFiled().set(configModel.getPropertyName(), properties.getProperty(configModel.getPropertyName()));
        configModel.getFiled().setAccessible(false);
    }
}
