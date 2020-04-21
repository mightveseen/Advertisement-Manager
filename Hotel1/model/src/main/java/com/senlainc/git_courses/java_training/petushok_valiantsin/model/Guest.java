package com.senlainc.git_courses.java_training.petushok_valiantsin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Guests")
public class Guest implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String secondName;
    @Column
    private LocalDate birthday;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return id == guest.id &&
                Objects.equals(firstName, guest.firstName) &&
                Objects.equals(secondName, guest.secondName) &&
                Objects.equals(birthday, guest.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, birthday);
    }

    @Override
    public String toString() {
        return this.id + ")" + this.firstName + ", "
                + this.secondName + ", " + this.birthday;
    }
}
