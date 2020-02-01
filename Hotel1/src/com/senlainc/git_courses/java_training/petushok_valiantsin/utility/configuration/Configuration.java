package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Configuration {
    private Properties properties = new Properties();
    private static final String PROPERTIES_PATH = "src/com/senlainc/git_courses/java_training/petushok_valiantsin/utility/configuration/program.properties";
    private static final Logger LOGGER = Logger.getLogger(RoomConfig.class.getSimpleName());

    public Configuration(String...propertyName) {
        try {
            readProperties(PROPERTIES_PATH);
            if (!checkProperty(propertyName)) {
                throw new NullPointerException("Property didn't exists");
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Could load program properties from file: " + e.toString(), e);
        } catch (NullPointerException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }
    public void readProperties(String path) throws IOException{
        final InputStream fileReader = new FileInputStream(path);
//        final ObjectInputStream propertiesReader = new ObjectInputStream(fileReader);
        properties.load(fileReader);
//        propertiesReader.close();
        fileReader.close();
    }

    public boolean checkProperty(String...propertyName) {
        if(properties.size() == 0) {
            return false;
        }
        Set<String> readProperties = properties.stringPropertyNames();
        Set<String> gottenProperties = new HashSet<>(Arrays.asList(propertyName));
        return readProperties.containsAll(gottenProperties);
    }

    public String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
