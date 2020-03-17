package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Guest")
public class Guest implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String secondName;
    @Column
    private LocalDate birthday;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "guest")
    private List<Order> order;

    public Guest() {

    }

    public Guest(String firstName, String secondName, LocalDate birthday) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
    }

    @Override
    public Guest clone() throws CloneNotSupportedException {
        return (Guest) super.clone();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int index) {
        this.id = index;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getSecondName() {
        return this.secondName;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    @Override
    public String toString() {
        return this.id + ")" + this.firstName + ", "
                + this.secondName + ", " + this.birthday;
    }
}
