package com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.MyList;

public interface IRoomDao {
    void create(Room room);

    void delete(int index);

    void update(Room room);

    MyList<Room> readAll();

    Room read(int index);
}
