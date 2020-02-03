package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.OrderStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.classindex.OrderIndex;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization.adapter.LocalDateAdapter;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization.adapter.LocalDateTimeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "order")
public class Order implements Cloneable {
    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "orderDate")
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    private LocalDateTime orderDate;
    @XmlElement(name = "guestIndex")
    private int guestIndex;
    @XmlElement(name = "roomIndex")
    private int roomIndex;
    @XmlElement(name = "startDate")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate startDate;
    @XmlElementWrapper(name = "attendanceList")
    @XmlElement(name = "attendanceIndex")
    private List<Integer> attendanceIndex = new LinkedList<>();
    @XmlElement(name = "endDate")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate endDate;
    @XmlElement(name = "status")
    private OrderStatus status;
    @XmlElement(name = "price")
    private double price;

    public Order() {
    }

    public Order(int guest, int room, LocalDate endDate, double price) {
        this.id = new OrderIndex().getIndex();
        this.orderDate = LocalDateTime.now();
        this.guestIndex = guest;
        this.roomIndex = room;
        this.startDate = LocalDate.now();
        this.endDate = endDate;
        this.status = OrderStatus.ACTIVE;
        this.price = price;
    }

    @Override
    public Order clone() throws CloneNotSupportedException {
        return (Order) super.clone();
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

    public List<Integer> getAttendanceIndex() {
        return this.attendanceIndex;
    }

    public void setAttendanceIndex(List<Integer> attendanceIndex) {
        this.attendanceIndex = attendanceIndex;
    }
}
