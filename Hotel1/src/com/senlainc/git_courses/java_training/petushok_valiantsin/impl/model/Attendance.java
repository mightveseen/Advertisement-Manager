package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model;

public class Attendance {
    private String name;
    private String section;
    private int price;

    public Attendance(String name, String section, int price) {
        this.name = name;
        this.section = section;
        this.price = price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + ", " + section + ", " + price;
    }
}
