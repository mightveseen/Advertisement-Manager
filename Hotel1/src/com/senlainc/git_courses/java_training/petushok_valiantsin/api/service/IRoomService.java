package com.senlainc.git_courses.java_training.petushok_valiantsin.api.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.status.Status;

import java.time.LocalDate;

public interface IRoomService {
    void add(Room room);

    void delete(int index);

    double getPrice(int index);

    Status getStatus(int index);

    void changePrice(int index, double price);

    void changeStatus(int index, Status status);

    void show(String parameter);

    Room getRoom(int index);

    void numFreeRoom();

//    void sort(String parameter);

    void showAfterDate(LocalDate freeDate);
}
