package com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Guest;

public interface IGuestDao {
    void create(Guest guest);
    void delete(int index);
    void update(int index, Guest guest);
    Guest[] readAll();
    Guest readById(int index);
}
