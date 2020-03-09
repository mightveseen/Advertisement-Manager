package com.senlainc.git_courses.java_training.petushok_valiantsin;

import java.time.LocalDate;

public class Guest implements Cloneable {
    private final String firstName;
    private final String secondName;
    private final LocalDate birthday;
    private int id;

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
