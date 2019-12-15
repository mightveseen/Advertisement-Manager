package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model;

import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.utility.MyList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private String orderDate;
    private int guestIndex;
    private int roomIndex;
    private short numberPerson;
    private MyList<Integer> attendanceIndex;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm / yyyy-MM-dd");

    public Order(Order order) {
        this.orderDate = order.getOrderDate();
        this.guestIndex = order.getGuestIndex();
        this.roomIndex = order.getRoomIndex();
        this.numberPerson = order.getNumberPerson();
        this.startDate = order.getStartDate();
        this.endDate = order.getEndDate();
        this.attendanceIndex = new MyList<>(order.getAttendanceIndex());
        this.price = order.getPrice();
    }

    public Order(int guest, int room, short numberPerson, LocalDate endDate, double price) {
        this.orderDate = LocalDateTime.now().format(formatter);
        this.guestIndex = guest;
        this.roomIndex = room;
        this.numberPerson = numberPerson;
        this.startDate = LocalDate.now();
        this.endDate = endDate;
        this.price = price;
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

    public short getNumberPerson() {
        return this.numberPerson;
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
        return "\n" + orderDate + ", " + guestIndex + ", " + roomIndex + ", [" + startDate + ", " + endDate + "]";
    }
}
