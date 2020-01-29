package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.logger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.logging.LogManager;

public class CustomLogger {

    static {
        try(InputStream configReader = new FileInputStream("src/com/senlainc/git_courses/java_training/petushok_valiantsin/utility/logger/log.properties")) {
//            FileHandler fileHandler = new FileHandler("log.txt");
//            fileHandler.setFormatter(new FileFormatter());
//            ConsoleHandler consoleHandler = new ConsoleHandler();
//            consoleHandler.setFormatter(new ConsoleFormatter());
//            LOGGER.addHandler(fileHandler);
//            LOGGER.addHandler(consoleHandler);
            LogManager.getLogManager().readConfiguration(configReader);
        } catch (Exception e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
    }
}
