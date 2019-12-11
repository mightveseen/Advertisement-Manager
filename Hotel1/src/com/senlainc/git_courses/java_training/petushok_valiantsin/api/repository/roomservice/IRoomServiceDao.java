package com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.roomservice;

import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.roomservice.RoomService;

public interface IRoomServiceDao {
    void addRoomService(RoomService roomService);
    void deleteRoomService(int index);
}
