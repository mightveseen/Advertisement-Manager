package com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.RoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.utility.MyList;

public interface IRoomServiceDao {
    void create(RoomService roomService);
    void delete(int index);
    void update(int index, RoomService roomService);
    MyList readAll();
    RoomService read(int index);
}
