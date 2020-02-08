package com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;

import java.util.List;

public interface IGuestDao {
    void create(Guest guest);

    void delete(int index);

    void update(Guest guest);

    List<Guest> readAll();

    Guest read(int index);

    void setAll();

    void saveAll();
}
