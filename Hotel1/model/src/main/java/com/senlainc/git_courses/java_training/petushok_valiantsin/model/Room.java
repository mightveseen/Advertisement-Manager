package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Room")
public class Room implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private int number;
    @Column
    private String classification;
    @Column(name = "room_number")
    private short roomNumber;
    @Column
    private short capacity;
    @Column
    @Enumerated(value = EnumType.STRING)
    private RoomStatus status;
    @Column
    private double price;

    public Room() {

    }

    public Room(int number, String classification, short roomNumber, short capacity, double price) {
        this.number = number;
        this.classification = classification;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.status = RoomStatus.FREE;
        this.price = price;
    }

    public Room(int number, String classification, short roomNumber, short capacity, RoomStatus status, double price) {
        this.number = number;
        this.classification = classification;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.status = status;
        this.price = price;
    }

    @Override
    public final Room clone() throws CloneNotSupportedException {
        return (Room) super.clone();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long index) {
        this.id = index;
    }

    public short getCapacity() {
        return this.capacity;
    }

    public int getNumber() {
        return this.number;
    }

    public String getClassification() {
        return this.classification;
    }

    public short getRoomNumber() {
        return this.roomNumber;
    }

    public RoomStatus getStatus() {
        return this.status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public final String toString() {
        return this.id + ")" + this.number + ", "
                + this.classification + ", " + this.roomNumber + ", "
                + this.capacity + ", " + this.status + ", " + this.price;
    }
}
