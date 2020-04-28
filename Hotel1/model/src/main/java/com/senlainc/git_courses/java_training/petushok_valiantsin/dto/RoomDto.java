package com.senlainc.git_courses.java_training.petushok_valiantsin.dto;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;

import java.io.Serializable;

public class RoomDto implements Serializable {

    private long id;
    private int number;
    private String classification;
    private short roomNumber;
    private short capacity;
    private RoomStatus status;
    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public short getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(short roomNumber) {
        this.roomNumber = roomNumber;
    }

    public short getCapacity() {
        return capacity;
    }

    public void setCapacity(short capacity) {
        this.capacity = capacity;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
