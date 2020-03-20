package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.OrderStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order implements Cloneable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    //    @ManyToMany
//    private List<Attendance> attendanceIndex = new ArrayList<>();
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
    @Column
    private double price;

    public Order() {

    }

    public Order(Guest guest, Room room, LocalDate endDate, double price) {
        this.orderDate = LocalDateTime.now();
        this.guest = guest;
        this.room = room;
        this.startDate = LocalDate.now();
        this.endDate = endDate;
        this.status = OrderStatus.ACTIVE;
        this.price = price;
    }

    @Override
    public Order clone() throws CloneNotSupportedException {
        return (Order) super.clone();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long index) {
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
//        return this.attendanceIndex;
        return null;
    }

    public void setAttendanceIndex(List<Attendance> attendanceIndex) {
//        this.attendanceIndex = attendanceIndex;
    }

    public String toString() {
        return "Order index: " + this.id + "\n" +
                "Order date: " + this.orderDate.format(DateTimeFormatter.ofPattern("HH:mm/yyyy-MM-dd")) + "\n" +
                "Guest: " + this.guest + "\n" +
                "Room: " + this.room + "\n" +
                "Start date: " + this.startDate + "\t" +
                "End date: " + this.endDate + "\n" +
                "Total amount: " + this.price + "\t" +
                "Status: " + this.status;
    }
}
