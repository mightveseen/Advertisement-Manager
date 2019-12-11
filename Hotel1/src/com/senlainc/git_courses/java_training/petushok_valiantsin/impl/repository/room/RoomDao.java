package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository.room;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.room.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.room.Room;

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
