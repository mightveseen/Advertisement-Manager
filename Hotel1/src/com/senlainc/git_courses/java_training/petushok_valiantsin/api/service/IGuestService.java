package com.senlainc.git_courses.java_training.petushok_valiantsin.api.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;

import java.time.LocalDate;
import java.util.List;

public interface IGuestService {
    void add(String firstName, String lastName, LocalDate birthday, String infoContact);

    void delete(int index);

    void changeInfoContact(int index, String information);

    int num();

    List<Guest> show();
}
