package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.classindex.RoomIndex;

public class Room {
    private final int id;
    private final int number;
    private final String classification;
    private final short roomNumber;
    private final short capacity;
    private RoomStatus status;
    private double price;

    public Room(Room room) {
        this.id = room.getId();
        this.number = room.getNumber();
        this.classification = room.getClassification();
        this.roomNumber = room.getRoomNumber();
        this.capacity = room.getCapacity();
        this.status = room.getStatus();
        this.price = room.getPrice();
    }

    public Room(int number, String classification, short roomNumber, short capacity, double price) {
        this.id = new RoomIndex().getIndex();
        this.number = number;
        this.classification = classification;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.status = RoomStatus.FREE;
        this.price = price;
    }

    public int getId() {
        return this.id;
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
        return id + ")" + number + ", "
                + classification + ", " + roomNumber + ", "
                + capacity + ", " + status.getName() + ", " + price;
    }
}
