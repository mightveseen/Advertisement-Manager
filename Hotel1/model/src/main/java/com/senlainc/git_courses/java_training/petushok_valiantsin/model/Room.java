package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;

public class Room implements Cloneable {
    private final int number;
    private final String classification;
    private final short roomNumber;
    private final short capacity;
    private int id;
    private RoomStatus status;
    private double price;

    public Room(int number, String classification, short roomNumber, short capacity, double price) {
        this.number = number;
        this.classification = classification;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.status = RoomStatus.FREE;
        this.price = price;
    }

    public Room(int number, String classification, short roomNumber, short capacity, RoomStatus status, double price) {
        this.number = number;
        this.classification = classification;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.status = status;
        this.price = price;
    }

    @Override
    public Room clone() throws CloneNotSupportedException {
        return (Room) super.clone();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int index) {
        this.id = index;
    }

    public short getCapacity() {
        return this.capacity;
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

    public RoomStatus getStatus() {
        return this.status;
    }

    public void setStatus(RoomStatus status) {
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
        return this.id + ")" + this.number + ", "
                + this.classification + ", " + this.roomNumber + ", "
                + this.capacity + ", " + this.status.toString() + ", " + this.price;
    }
}
