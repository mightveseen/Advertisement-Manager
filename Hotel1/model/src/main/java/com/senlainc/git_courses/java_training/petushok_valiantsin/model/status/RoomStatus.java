package com.senlainc.git_courses.java_training.petushok_valiantsin.model.status;

public enum RoomStatus {

    FREE("Free"),
    RENTED("Rented"),
    SERVED("Served");

    private final String name;

    RoomStatus(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
