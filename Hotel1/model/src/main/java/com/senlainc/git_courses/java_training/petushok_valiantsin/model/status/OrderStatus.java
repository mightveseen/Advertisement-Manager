package com.senlainc.git_courses.java_training.petushok_valiantsin.model.status;

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
}
