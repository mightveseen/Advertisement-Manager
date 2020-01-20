package com.senlainc.git_courses.java_training.petushok_valiantsin.utility;

public enum ConsoleColor {
    GREEN("\u001B[92m"),
    RED("\u001B[91m"),
    YELLOW("\u001B[93m"),
    RESET("\u001B[0m");

    private final String code;

    ConsoleColor(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
