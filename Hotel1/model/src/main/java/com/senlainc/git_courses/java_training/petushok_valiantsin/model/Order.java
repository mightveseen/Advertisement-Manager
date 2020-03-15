package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order implements Cloneable {

    private final LocalDateTime orderDate;
    private final Guest guest;
    private final Room room;
    private final LocalDate startDate;
    private int id;
    private List<Attendance> attendanceIndex = new ArrayList<>();
    private LocalDate endDate;
    private OrderStatus status;
    private double price;

    public Order(Guest guest, Room room, LocalDate endDate, double price) {
        this.orderDate = LocalDateTime.now();
        this.guest = guest;
        this.room = room;
        this.startDate = LocalDate.now();
        this.endDate = endDate;
        this.status = OrderStatus.ACTIVE;
        this.price = price;
    }

    public Order(LocalDateTime orderDate, Guest guest, Room room, LocalDate startDate, LocalDate endDate, OrderStatus status, double price) {
        this.orderDate = orderDate;
        this.guest = guest;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.price = price;
    }

    @Override
    public Order clone() throws CloneNotSupportedException {
        return (Order) super.clone();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int index) {
        this.id = index;
    }

    public Guest getGuest() {
        return this.guest;
    }

    public Room getRoom() {
        return room;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getOrderDate() {
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

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Attendance> getAttendanceIndex() {
        return this.attendanceIndex;
    }

    public void setAttendanceIndex(List<Attendance> attendanceIndex) {
        this.attendanceIndex = attendanceIndex;
    }

    public String toString() {
        return "Order index: " + this.id + "\n" +
                "Order date: " + this.orderDate.format(DateTimeFormatter.ofPattern("HH:mm/yyyy-MM-dd")) + "\n" +
                "Guest: " + this.guest + "\n" +
                "Room: " + this.room + "\n" +
                "Start date: " + this.startDate + "\t" +
                "End date: " + this.endDate + "\n" +
                "Total amount: " + this.price + "\t" +
                "Status: " + this.status.toString();
    }
}
