package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Attendances")
public class Attendance implements Cloneable, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String section;
    @Column
    private double price;
    @ManyToMany(mappedBy = "attendances")
    private Set<Order> order;

    public Attendance() {

    }

    public Attendance(String name, String section, double price) {
        this.name = name;
        this.section = section;
        this.price = price;
    }

    @Override
    public Attendance clone() throws CloneNotSupportedException {
        return (Attendance) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendance that = (Attendance) o;
        return id == that.id &&
                Double.compare(that.price, price) == 0 &&
                Objects.equals(name, that.name) &&
                Objects.equals(section, that.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, section, price);
    }

    public long getId() {
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