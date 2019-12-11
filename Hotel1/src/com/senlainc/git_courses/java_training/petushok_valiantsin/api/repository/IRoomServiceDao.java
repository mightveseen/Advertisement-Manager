package com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.RoomService;

public interface IRoomServiceDao {
    void create(RoomService roomService);
    void delete(int index);
    void update(int index, RoomService roomService);
    RoomService[] readAll();
    RoomService readById(int index);
}
