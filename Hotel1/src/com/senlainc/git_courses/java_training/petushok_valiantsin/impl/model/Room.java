package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model;

import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status.Status;

public class Room {
    private short number;
    private String classification;
    private int roomNumber;
    private Status status;

    public Room(short number, String classification, int roomNumber, Status status) {
        this.number = number;
        this.classification = classification;
        this.roomNumber = roomNumber;
        this.status = status;
    }
}
