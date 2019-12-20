package com.senlainc.git_courses.java_training.petushok_valiantsin.api.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Status;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.MyList;

public interface IRoomService {
    void add(Room room);

    void delete(int index);

    double getPrice(int index);

    int getSize();

    Status.StatusType getStatus(int index);

    void changePrice(int index, double price);

    void changeStatus(int index, Status.StatusType status);

    void show(String parameter);

    void show(MyList<Room> myList);

    Room getRoom(int index);

    void numFreeRoom();

    MyList<Room> sort(String parameter);
}
