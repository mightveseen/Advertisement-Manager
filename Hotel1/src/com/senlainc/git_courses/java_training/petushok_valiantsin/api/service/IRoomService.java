package com.senlainc.git_courses.java_training.petushok_valiantsin.api.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status.Status;

import java.time.LocalDate;

public interface IRoomService {
    void add(Room room);

    void delete(int index);

    double getPrice(int index);

    Status getStatus(int index);

    void changePrice(int index, double price);

    void changeStatus(int index, Status status);

    void showRoom(String parameter);

    void numFreeRoom();

    void sortRoom(String parameter);

    void showAfterDate(LocalDate freeDate);
}
