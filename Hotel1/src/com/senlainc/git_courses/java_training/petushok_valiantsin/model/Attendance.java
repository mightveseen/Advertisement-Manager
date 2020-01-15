package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.classindex.AttendanceIndex;

public class Attendance {
    private final int id;
    private final String name;
    private final String section;
    private double price;

    public Attendance(Attendance attendance) {
        this.id = attendance.getId();
        this.name = attendance.getName();
        this.price = attendance.getPrice();
        this.section = attendance.getSection();
    }

    public Attendance(String name, String section, double price) {
        this.id = new AttendanceIndex().getIndex();
        this.name = name;
        this.section = section;
        this.price = price;
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
