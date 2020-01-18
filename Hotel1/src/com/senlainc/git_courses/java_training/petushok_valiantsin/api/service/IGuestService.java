package com.senlainc.git_courses.java_training.petushok_valiantsin.api.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;

import java.time.LocalDate;

public interface IGuestService {
    void add(String firstName, String lastName, LocalDate birthday, String infoContact);

    void delete(int index);

    void changeInfoContact(int index, String information);

    void num();

    void show();

    Guest getGuest(int index);

    int[] sortByAlphabet();
}
