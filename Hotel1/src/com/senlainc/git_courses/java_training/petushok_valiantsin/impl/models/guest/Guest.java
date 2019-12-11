package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.models.guest;

import java.util.Date;

public class Guest {
    private String firstName;
    private String secondName;
    private Date birthday;
    private short numberPerson;
    private String infoContact;

    public Guest(String firstName, String secondName, Date birthday, short numberPerson, String infoContact) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.numberPerson = numberPerson;
        this.infoContact = infoContact;
    }
}
