package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.logger;

import java.io.IOException;
import java.io.InputStream;

public class CustomLogger {
    private CustomLogger() {
        throw new IllegalStateException("Utility class");
    }

    public static void execute() {
        try (InputStream configReader = CustomLogger.class.getResourceAsStream("/xml/Log4j")) {

        } catch (IOException e) {
            System.err.println("Could not setup logger res.configuration: " + e.toString());
        }
    }
}
