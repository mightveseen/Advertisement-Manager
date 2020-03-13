package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.logger;

import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.FileNotExistException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;

public class CustomLogger {
    private CustomLogger() {
        throw new IllegalStateException("Utility class");
    }

    private static final Logger LOGGER = LogManager.getLogger(CustomLogger.class.getName());

    public static void execute() {
        final URL configReader = CustomLogger.class.getResource("/xml/log4j2.xml");
        try {
            if (configReader == null) {
                throw new FileNotExistException("Couldn't load property file for Logger. Use default properties.");
            }
            System.setProperty("log4j.configurationFile", configReader.getPath());
        } catch (FileNotExistException e) {
            LOGGER.log(Level.WARN, e.getMessage(), e);
        }
    }
}
