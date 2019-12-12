package com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.utility.MyList;

public interface IGuestDao {
    void create(Guest guest);
    void delete(int index);
    void update(int index, Guest guest);
    MyList readAll();
    Guest read(int index);
}
