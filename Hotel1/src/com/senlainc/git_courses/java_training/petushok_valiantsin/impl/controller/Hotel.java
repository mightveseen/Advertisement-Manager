package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status.Free;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status.Rented;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status.Served;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository.RoomDao;

import java.time.LocalDate;
import java.time.LocalTime;

public class Hotel {
    public static void main(String[] args) {
        RoomDao roomDao = new RoomDao();
        roomDao.create(new Room(103, "President", (short) 5,
                new Served("Change shower", LocalTime.of(21, 11, 3))));
        roomDao.create(new Room(203, "President", (short) 5,
                new Rented(LocalDate.of(2020, 11, 3))));
        roomDao.update(1, new Room(303, "President", (short) 5,
                new Free()));
        roomDao.update(2, new Room(403, "President", (short) 5,
                new Rented(LocalDate.of(2020, 11, 3))));
        roomDao.delete(1);
        System.out.println(roomDao.readAll());
    }
}
