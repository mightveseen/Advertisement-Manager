package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.MyList;

public class RoomDao implements IRoomDao {
    private final MyList<Room> roomMyList = new MyList<>();

    @Override
    public void create(Room room) {
        roomMyList.add(room);
    }

    @Override
    public void delete(int index) {
        roomMyList.remove(index);
    }

    @Override
    public void update(int index, Room room) {
        roomMyList.add(index, room);
    }

    @Override
    public MyList<Room> readAll() {
        return roomMyList;
    }

    @Override
    public Room read(int index) {
        return roomMyList.get(index);
    }
}
