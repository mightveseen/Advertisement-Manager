package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IRoomServiceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.RoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.utility.MyList;

public class RoomServiceDao implements IRoomServiceDao {
    private MyList<RoomService> roomServiceMyList = new MyList<>();

    @Override
    public void create(RoomService roomService) {
        roomServiceMyList.add(roomService);
    }
    @Override
    public void delete(int index) {
        roomServiceMyList.remove(index);
    }
    @Override
    public void update(int index, RoomService roomService) {
        roomServiceMyList.add(index, roomService);
    }
    @Override
    public MyList readAll() {
        return roomServiceMyList;
    }
    @Override
    public RoomService read(int index) {
        return roomServiceMyList.get(index);
    }
}
