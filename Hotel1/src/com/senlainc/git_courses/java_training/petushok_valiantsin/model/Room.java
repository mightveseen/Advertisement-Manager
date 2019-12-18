package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.Status;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.classindex.RoomIndex;

public class Room {
    private int id = new RoomIndex().getIndex();
    private final int number;
    private final String classification;
    private final short roomNumber;
    private Status status;
    private double price;

    public Room(Room room) {
        this.id = room.getId();
        this.number = room.getNumber();
        this.classification = room.getClassification();
        this.roomNumber = room.getRoomNumber();
        this.status = room.getStatus();
        this.price = room.getPrice();
    }

    public Room(int number, String classification, short roomNumber, Status status, double price) {
        this.number = number;
        this.classification = classification;
        this.roomNumber = roomNumber;
        this.status = status;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public int getNumber() {
        return this.number;
    }

    public String getClassification() {
        return this.classification;
    }

    public short getRoomNumber() {
        return this.roomNumber;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "\n" + id + ")" + number + ", "
                + classification + ", " + roomNumber + ", "
                + status + ", " + price;
    }
}
