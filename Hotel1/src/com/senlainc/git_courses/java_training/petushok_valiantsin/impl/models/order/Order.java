package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.models.order;

import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.models.guest.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.models.room.Room;

import java.time.LocalDateTime;

public class Order {
    private LocalDateTime orderDate;
    private Guest guest;
    private Room room;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Order(LocalDateTime orderDate, Guest guest, Room room, LocalDateTime endDate) {
        this.orderDate = orderDate;
        this.guest = guest;
        this.room = room;
        this.startDate = orderDate;
        this.endDate = endDate;
    }
}
