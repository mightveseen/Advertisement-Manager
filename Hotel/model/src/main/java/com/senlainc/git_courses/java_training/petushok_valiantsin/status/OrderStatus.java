package com.senlainc.git_courses.java_training.petushok_valiantsin.status;

import com.senlainc.git_courses.java_training.petushok_valiantsin.console.ConsoleColor;

public enum OrderStatus {
    ACTIVE("Active"),
    DISABLED("Disabled");

    private final String name;

    OrderStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getColorName() {
        if (OrderStatus.this == OrderStatus.DISABLED) {
            return ConsoleColor.RED.getCode() + this.name + ConsoleColor.RESET.getCode();
        }
        return ConsoleColor.GREEN.getCode() + this.name + ConsoleColor.RESET.getCode();
    }
}
