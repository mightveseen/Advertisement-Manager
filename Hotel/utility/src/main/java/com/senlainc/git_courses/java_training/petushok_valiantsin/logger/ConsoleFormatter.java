package com.senlainc.git_courses.java_training.petushok_valiantsin.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class ConsoleFormatter extends Formatter {
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss/yyyy-MM-dd");

    @Override
    public String format(LogRecord record) {
        return LocalDateTime.now().format(timeFormatter) + " - " +
                record.getLevel() + " - " + record.getMessage() + "\n";
    }
}
