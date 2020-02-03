package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public class CustomLogger {
    static {
        try (InputStream configReader = new FileInputStream("src/resources/properties/logger/log.properties")) {
            LogManager.getLogManager().readConfiguration(configReader);
        } catch (IOException e) {
            System.err.println("Could not setup logger res.configuration: " + e.toString());
        }
    }
}
