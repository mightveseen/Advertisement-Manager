package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model;

import java.time.LocalDate;

public class Guest {
    private String firstName;
    private String secondName;
    private LocalDate birthday;
    private short numberPerson;
    private String infoContact;

    public Guest(String firstName, String secondName, LocalDate birthday, short numberPerson, String infoContact) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.numberPerson = numberPerson;
        this.infoContact = infoContact;
    }

    @Override
    public String toString() {
        return firstName + ", " + secondName + ", " + birthday + ", " + numberPerson + ", " + infoContact;
    }
}
