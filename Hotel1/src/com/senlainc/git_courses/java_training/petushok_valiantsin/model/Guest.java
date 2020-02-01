package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.classindex.GuestIndex;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization.adapter.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "guest")
public class Guest {
    @XmlElement(name = "id")
    private final int id;
    @XmlElement(name = "firstName")
    private final String firstName;
    @XmlElement(name = "secondName")
    private final String secondName;
    @XmlElement(name = "birthday")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private final LocalDate birthday;
    @XmlElement(name = "infoContact")
    private String infoContact;

    public Guest(Guest guest) {
        this.id = guest.getId();
        this.firstName = guest.getFirstName();
        this.secondName = guest.getSecondName();
        this.birthday = guest.getBirthday();
        this.infoContact = guest.getInfoContact();
    }

    public Guest(String firstName, String secondName, LocalDate birthday, String infoContact) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
        return id + ")" + firstName + ", "
                + secondName + ", " + birthday + ", " + infoContact;
    }
}
