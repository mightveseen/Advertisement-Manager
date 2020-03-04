package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

import java.time.LocalDate;

public class Guest implements Cloneable {
    private int id;
    private String firstName;
    private String secondName;
    private LocalDate birthday;
    private String infoContact;

    public Guest(String firstName, String secondName, LocalDate birthday, String infoContact) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.infoContact = infoContact;
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

    public String getInfoContact() {
        return this.infoContact;
    }

    public void setInfoContact(String infoContact) {
        this.infoContact = infoContact;
    }

    @Override
    public String toString() {
        return this.id + ")" + this.firstName + ", "
                + this.secondName + ", " + this.birthday + ", " + this.infoContact;
    }
}
