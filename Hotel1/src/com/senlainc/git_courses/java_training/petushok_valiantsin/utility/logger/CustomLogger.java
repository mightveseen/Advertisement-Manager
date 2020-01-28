package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.logger;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLogger {
    private static final Logger LOGGER = Logger.getLogger(CustomLogger.class.getName());
    private static CustomLogger instance;

    private CustomLogger() {
        try {
            FileHandler handler = new FileHandler("log.txt");
            LOGGER.addHandler(handler);
        } catch (Exception ignored) {
        }
    }

    public static CustomLogger getInstance() {
        if (instance == null) {
            instance = new CustomLogger();
        }
        return instance;
    }

    public void setInfo(String reason) {
        LOGGER.log(Level.INFO, "ssss");
    }
}
