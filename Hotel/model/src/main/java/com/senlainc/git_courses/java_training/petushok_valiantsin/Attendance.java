package com.senlainc.git_courses.java_training.petushok_valiantsin;

import java.io.Serializable;

public class Attendance implements Cloneable, Serializable {
    private final String name;
    private final String section;
    private int id;
    private double price;

    public Attendance(String name, String section, double price) {
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

    public void setId(int index) {
        this.id = index;
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
        return this.id + ")" + this.name + ", " + this.section + ", " + this.price;
    }
}
