package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status.Rented;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository.RoomDao;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Hotel {
    public static void main(String[] args) {
        RoomDao roomDao = new RoomDao();
        roomDao.create(new Room((short) 103, "President", 5,
                new Rented(LocalDate.of(2020, 11, 3))));
        roomDao.create(new Room((short) 203, "President", 5,
                new Rented(LocalDate.of(2020, 11, 3))));
        roomDao.create(new Room((short) 303, "President", 5,
                new Rented(LocalDate.of(2020, 11, 3))));
        roomDao.update(2, new Room((short) 403, "President", 5,
                new Rented(LocalDate.of(2020, 11, 3))));
        roomDao.delete(1);
    }
}
