package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.OrderStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.classindex.OrderIndex;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "order")
public class Order {
    @XmlElement(name = "id")
    private final int id;
    @XmlElement(name = "orderDate")
    private final String orderDate;
    @XmlElement(name = "guestIndex")
    private final int guestIndex;
    @XmlElement(name = "roomIndex")
    private final int roomIndex;
    @XmlElement(name = "startDate")
    private final LocalDate startDate;
    @XmlElementWrapper(name = "attendanceList")
    @XmlElement(name = "attendanceIndex")
    private List<Integer> attendanceIndex = new LinkedList<>();
    @XmlElement(name = "endDate")
    private LocalDate endDate;
    @XmlElement(name = "status")
    private OrderStatus status;
    @XmlElement(name = "price")
    private double price;

    public Order(Order order) {
        this.id = order.getId();
        this.orderDate = order.getOrderDate();
        this.guestIndex = order.getGuestIndex();
        this.roomIndex = order.getRoomIndex();
        this.startDate = order.getStartDate();
        this.endDate = order.getEndDate();
        this.attendanceIndex = new LinkedList<>(order.getAttendanceIndex());
        this.status = order.getStatus();
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
        this.status = OrderStatus.ACTIVE;
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

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Integer> getAttendanceIndex() {
        return this.attendanceIndex;
    }

    public void setAttendanceIndex(List<Integer> attendanceIndex) {
        this.attendanceIndex = attendanceIndex;
    }
}
