package com.senlainc.git_courses.java_training.petushok_valiantsin.model.status;

import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.console.ConsoleColor;

public enum OrderStatus {
    ACTIVE("Active"),
    DISABLED("Disabled");

    private final String name;

    OrderStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        if (OrderStatus.this == OrderStatus.DISABLED) {
            return ConsoleColor.RED.getCode() + this.name + ConsoleColor.RESET.getCode();
        }
        return ConsoleColor.GREEN.getCode() + this.name + ConsoleColor.RESET.getCode();
    }
}
