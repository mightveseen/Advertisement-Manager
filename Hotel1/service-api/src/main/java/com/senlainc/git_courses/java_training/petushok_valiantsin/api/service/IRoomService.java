package com.senlainc.git_courses.java_training.petushok_valiantsin.api.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;

import java.util.List;

public interface IRoomService extends IGenericService<Room, Long> {

    void changeStatus(long index, String status);

    Long getNumFree();

    RoomStatus getRoomStatus(long index);

    Double gerRoomPrice(long index);

    List<Room> readAll(String type, int firstElement, int maxResult);

    List<Room> readAllSorted(String type, int firstElement, int maxResult, String parameter);
}
