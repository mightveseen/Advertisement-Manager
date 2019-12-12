package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model;

import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status.Status;

public class Room {
    private int number;
    private String classification;
    private short roomNumber;
    private Status status;

    public Room(int number, String classification, short roomNumber, Status status) {
        this.number = number;
        this.classification = classification;
        this.roomNumber = roomNumber;
        this.status = status;
    }

    @Override
    public String toString() {
        return number + ", " + classification + ", " + roomNumber + ", " + status;
    }
}
