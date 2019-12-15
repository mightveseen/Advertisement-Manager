package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model;

public class Attendance {
    private final String name;
    private final String section;
    private double price;

    public Attendance(Attendance attendance) {
        this.name = attendance.getName();
        this.price = attendance.getPrice();
        this.section = attendance.getSection();
    }

    public Attendance(String name, String section, double price) {
        this.name = name;
        this.section = section;
        this.price = price;
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
        return "\n" + name + ", " + section + ", " + price;
    }
}
