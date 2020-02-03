package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.classindex.RoomIndex;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "room")
public class Room implements Cloneable{
    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "number")
    private int number;
    @XmlElement(name = "classification")
    private String classification;
    @XmlElement(name = "roomNumber")
    private short roomNumber;
    @XmlElement(name = "capacity")
    private short capacity;
    @XmlElement(name = "status")
    private RoomStatus status;
    @XmlElement(name = "price")
    private double price;

    public Room() {
    }

    public Room(int number, String classification, short roomNumber, short capacity, double price) {
        this.id = new RoomIndex().getIndex();
        this.number = number;
        this.classification = classification;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.status = RoomStatus.FREE;
        this.price = price;
    }

    @Override
    public Room clone() throws CloneNotSupportedException{
        return (Room) super.clone();
    }

    public int getId() {
        return this.id;
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
    public String toString() {
        return id + ")" + number + ", "
                + classification + ", " + roomNumber + ", "
                + capacity + ", " + status.toString() + ", " + price;
    }
}
