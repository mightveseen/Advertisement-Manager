package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.mylist.MyList;

public class RoomDao implements IRoomDao {
    private MyList<Room> roomMyList = new MyList<>();

    @Override
    public void create(Room room) {
        roomMyList.add(room);
    }
    @Override
    public void delete(int index) {

    }
    @Override
    public void update(int index, Room room) {

    }
    @Override
    public MyList readAll() {
        return roomMyList;
    }
    @Override
    public Room readById(int index) {
        return roomMyList.get(index);
    }
}
