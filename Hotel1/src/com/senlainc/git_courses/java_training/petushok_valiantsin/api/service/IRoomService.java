package com.senlainc.git_courses.java_training.petushok_valiantsin.api.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Status;

import java.util.List;

public interface IRoomService {
    void add(Room room);

    void delete(int index);

    double getPrice(int index);

    int getSize();

    Status.RoomStatus getStatus(int index);

    void changePrice(int index, double price);

    void changeStatus(int index, Status.RoomStatus status);

    void show(String parameter);

    void show(List<Room> myList);

    Room getRoom(int index);

    void numFreeRoom();

    List<Room> sort(String parameter);
}
