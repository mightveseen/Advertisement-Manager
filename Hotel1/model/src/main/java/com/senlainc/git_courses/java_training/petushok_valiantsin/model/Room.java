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

    public Room(final int number, final String classification,
                final short roomNumber, final short capacity,
                final double price) {
        this.number = number;
        this.classification = classification;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.status = RoomStatus.FREE;
        this.price = price;
    }

    public Room(final int number, final String classification,
                final short roomNumber, final short capacity,
                final RoomStatus status, final double price) {
        this.number = number;
        this.classification = classification;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.status = status;
        this.price = price;
    }

    @Override
    public final Room clone() throws CloneNotSupportedException {
        return (Room) super.clone();
    }

    public final int getId() {
        return this.id;
    }

    public final void setId(int index) {
        this.id = index;
    }

    public final short getCapacity() {
        return this.capacity;
    }

    public final int getNumber() {
        return this.number;
    }

    public final String getClassification() {
        return this.classification;
    }

    public final short getRoomNumber() {
        return this.roomNumber;
    }

    public final RoomStatus getStatus() {
        return this.status;
    }

    public final void setStatus(RoomStatus status) {
        this.status = status;
    }

    public final double getPrice() {
        return this.price;
    }

    public final void setPrice(double price) {
        this.price = price;
    }

    @Override
    public final String toString() {
        return this.id + ")" + this.number + ", "
                + this.classification + ", " + this.roomNumber + ", "
                + this.capacity + ", " + this.status.toString() + ", " + this.price;
    }
}
