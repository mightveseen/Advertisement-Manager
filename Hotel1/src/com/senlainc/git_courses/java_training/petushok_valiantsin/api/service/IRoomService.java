package com.senlainc.git_courses.java_training.petushok_valiantsin.api.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;

import java.util.List;

public interface IRoomService {
    void add(Room room);

    void delete(int index);

    List<Room> getRoomList();

    void changePrice(int index, double price);

    void changeStatus(int index, RoomStatus status);

    List<Room> getRoomList(String parameter);

    long numFreeRoom();

    List<Room> sort(String type, String parameter);
}
