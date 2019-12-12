package com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.utility.MyList;

public interface IRoomDao {
    void create(Room room);
    void delete(int index);
    void update(int index, Room room);
    MyList readAll();
    Room readById(int index);
}
