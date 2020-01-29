package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.logger;

import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class CustomLogger {
    private static final Logger LOGGER = Logger.getLogger(CustomLogger.class.getSimpleName());

    static {
        try {
//            FileHandler fileHandler = new FileHandler("log.txt");
//            fileHandler.setFormatter(new FileFormatter());
//            ConsoleHandler consoleHandler = new ConsoleHandler();
//            consoleHandler.setFormatter(new ConsoleFormatter());
//            LOGGER.addHandler(fileHandler);
//            LOGGER.addHandler(consoleHandler);
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/com/senlainc/git_courses/java_training/petushok_valiantsin/utility/logger/log.properties"));
        } catch (Exception ignored) {
        }
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
