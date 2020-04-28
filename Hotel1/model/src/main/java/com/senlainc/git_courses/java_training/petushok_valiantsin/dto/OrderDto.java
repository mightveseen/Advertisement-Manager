package com.senlainc.git_courses.java_training.petushok_valiantsin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.OrderStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDto implements Serializable {

    private long id;
    private RoomDto room;
    private GuestDto guest;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm/yyyy-MM-dd")
    private LocalDateTime orderDate;
    private List<AttendanceDto> attendances;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private OrderStatus status;
    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoomDto getRoom() {
        return room;
    }

    public void setRoom(RoomDto room) {
        this.room = room;
    }

    public GuestDto getGuest() {
        return guest;
    }

    public void setGuest(GuestDto guest) {
        this.guest = guest;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<AttendanceDto> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<AttendanceDto> attendances) {
        this.attendances = attendances;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
