package com.senlainc.gitcourses.javatraining.petushokvaliantsin.dto;

import java.io.Serializable;

public class AttendanceDto implements Serializable {

    private long id;
    private String name;
    private String section;
    private double price;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
