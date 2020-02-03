package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class FileFormatter extends Formatter {
    public static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss/yyyy-MM-dd");

    @Override
    public String format(LogRecord record) {
        StringBuilder mainBuilder = new StringBuilder();
        mainBuilder.append(LocalDateTime.now().format(timeFormatter)).append(" - ");
        mainBuilder.append("[").append(record.getSourceClassName()).append("]").append(" - ");
        mainBuilder.append(record.getLevel()).append(" - ").append(record.getMessage()).append("\n");
        if (record.getThrown() != null) {
            mainBuilder.append("{\n").append("\t").append(record.getThrown()).append("\n");
            for (StackTraceElement reason : record.getThrown().getStackTrace()) {
                mainBuilder.append("\t").append(reason.toString()).append("\n");
            }
            mainBuilder.append("}\n");
        }
        return mainBuilder.toString();
    }
}
