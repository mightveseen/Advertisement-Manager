package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Room;

public class RoomDao implements IRoomDao {
    private Room roomArray[] = new Room[256];

    @Override
    public void create(Room room) {

    }
    @Override
    public void delete(int index) {

    }
    @Override
    public void update(int index, Room room) {

    }
    @Override
    public Room[] readAll() {
        return roomArray;
    }
    @Override
    public Room readById(int index) {
        return roomArray[index];
    }
}
