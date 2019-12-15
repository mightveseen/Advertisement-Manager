package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model;

import java.time.LocalDate;

public class Guest {
    private final String firstName;
    private final String secondName;
    private final LocalDate birthday;
    private String infoContact;

    public Guest(Guest guest) {
        this.firstName = guest.getFirstName();
        this.secondName = guest.getSecondName();
        this.birthday = guest.getBirthday();
        this.infoContact = guest.getInfoContact();
    }

    public Guest(String firstName, String secondName, LocalDate birthday, String infoContact) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.infoContact = infoContact;
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
        return "\n" + firstName + ", " + secondName + ", " + birthday + ", " + infoContact;
    }
}
