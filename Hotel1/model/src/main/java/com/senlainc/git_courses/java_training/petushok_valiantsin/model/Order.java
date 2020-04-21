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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Orders")
public class Order implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "OrderAttendances",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "attendance_id"))
    private List<Attendance> attendances;
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

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Attendance> getAttendances() {
        return this.attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    @Override
    public Order clone() throws CloneNotSupportedException {
        return (Order) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Double.compare(order.price, price) == 0 &&
                Objects.equals(room, order.room) &&
                Objects.equals(guest, order.guest) &&
                Objects.equals(startDate, order.startDate) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(attendances, order.attendances) &&
                Objects.equals(endDate, order.endDate) &&
                status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(room, guest, startDate, id, orderDate, attendances, endDate, status, price);
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
