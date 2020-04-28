package com.senlainc.git_courses.java_training.petushok_valiantsin.api.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.RoomStatus;

import java.util.List;

public interface IRoomService {

    void create(int number, String classification, short roomNumber, short capacity, double price);

    void delete(long index);

    void changePrice(long index, double price);

    void changeStatus(long index, String status);

    Long getNumFree();

    RoomStatus getRoomStatus(long index);

    Room getRoom(long index);

    List<Room> getRooms(String parameter);

    List<Room> getSortedRooms(String type, String parameter);
}
