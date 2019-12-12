package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Order {
    private LocalDateTime orderDate;
    private Guest guest;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;

    public Order(Guest guest, Room room, LocalDate endDate) {
        this.orderDate = LocalDateTime.now();
        this.guest = guest;
        this.room = room;
        this.startDate = LocalDate.now();
        this.endDate = endDate;
    }
    @Override
    public String toString() {
        return orderDate + ", " + guest + ", " + room + ", [" + startDate + ", " + endDate + "]";
    }
}
