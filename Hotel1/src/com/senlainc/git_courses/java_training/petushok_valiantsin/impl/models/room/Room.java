package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.models.room;

import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.models.status.Status;

public class Room {
    private int number;
    private String classification;
    private int roomNumber;
    private Status status;

    public Room(int number, String classification, int roomNumber, Status status) {
        this.number = number;
        this.classification = classification;
        this.roomNumber = roomNumber;
        this.status = status;
    }
}
