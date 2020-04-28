package com.senlainc.git_courses.java_training.petushok_valiantsin.dto;

import java.util.Set;

public class RoomDto {

    private String id;
    private String number;
    private String classification;
    private String roomNumber;
    private String capacity;
    private String status;
    private String price;
    private Set<String> orders;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Set<String> getOrders() {
        return orders;
    }

    public void setOrders(Set<String> orders) {
        this.orders = orders;
    }
}
