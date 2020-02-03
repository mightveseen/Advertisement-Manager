package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.classindex.AttendanceIndex;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "attendance")
public class Attendance implements Cloneable {
    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "section")
    private String section;
    @XmlElement(name = "price")
    private double price;

    public Attendance() {

    }

    public Attendance(String name, String section, double price) {
        this.id = new AttendanceIndex().getIndex();
        this.name = name;
        this.section = section;
        this.price = price;
    }

    @Override
    public Attendance clone() throws CloneNotSupportedException {
        return (Attendance) super.clone();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getSection() {
        return this.section;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id + ")" + name + ", " + section + ", " + price;
    }
}
