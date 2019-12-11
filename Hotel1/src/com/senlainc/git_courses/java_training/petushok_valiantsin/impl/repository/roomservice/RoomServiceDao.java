package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository.roomservice;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.roomservice.IRoomServiceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.roomservice.RoomService;

public class RoomServiceDao implements IRoomServiceDao {
    private RoomService roomServiceArray[] = new RoomService[256];

    @Override
    public void create(RoomService roomService) {

    }
    @Override
    public void delete(int index) {

    }
    private void offsetArray() {

    }
    @Override
    public void update(int index, RoomService roomService) {

    }
    @Override
    public RoomService[] readAll() {
        return roomServiceArray;
    }
}
