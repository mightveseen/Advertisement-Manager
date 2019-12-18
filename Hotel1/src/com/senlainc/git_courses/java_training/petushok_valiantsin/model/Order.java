package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.MyList;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.classindex.OrderIndex;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private final int id;
    private final String orderDate;
    private final int guestIndex;
    private final int roomIndex;
    private MyList<Integer> attendanceIndex;
    private final LocalDate startDate;
    private LocalDate endDate;
    private double price;

    public Order(Order order) {
        this.id = order.getId();
        this.orderDate = order.getOrderDate();
        this.guestIndex = order.getGuestIndex();
        this.roomIndex = order.getRoomIndex();
        this.startDate = order.getStartDate();
        this.endDate = order.getEndDate();
        this.attendanceIndex = new MyList<>(order.getAttendanceIndex());
        this.price = order.getPrice();
    }

    public Order(int guest, int room, LocalDate endDate, double price) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm / yyyy-MM-dd");
        this.id = new OrderIndex().getIndex();
        this.orderDate = LocalDateTime.now().format(formatter);
        this.guestIndex = guest;
        this.roomIndex = room;
        this.startDate = LocalDate.now();
        this.endDate = endDate;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public int getGuestIndex() {
        return this.guestIndex;
    }

    public int getRoomIndex() {
        return roomIndex;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOrderDate() {
        return this.orderDate;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public MyList<Integer> getAttendanceIndex() {
        return this.attendanceIndex;
    }

    public void setAttendanceIndex(MyList<Integer> attendanceIndex) {
        this.attendanceIndex = attendanceIndex;
    }

    @Override
    public String toString() {
        return "\n" + id + ")" + orderDate + ", "
                + guestIndex + ", " + roomIndex + ", ["
                + startDate + ", " + endDate + "], " + price;
    }
}
