package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.classindex.GuestIndex;

import java.time.LocalDate;

public class Guest {
    private final int id;
    private final String firstName;
    private final String secondName;
    private final LocalDate birthday;
    private String infoContact;

    public Guest(Guest guest) {
        this.id = guest.getId();
        this.firstName = guest.getFirstName();
        this.secondName = guest.getSecondName();
        this.birthday = guest.getBirthday();
        this.infoContact = guest.getInfoContact();
    }

    public Guest(String firstName, String secondName, LocalDate birthday, String infoContact) {
        this.id = new GuestIndex().getIndex();
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.infoContact = infoContact;
    }

    public int getId() {
        return this.id;
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
        return "\n" + id + ")" + firstName + ", "
                + secondName + ", " + birthday + ", " + infoContact;
    }
}
