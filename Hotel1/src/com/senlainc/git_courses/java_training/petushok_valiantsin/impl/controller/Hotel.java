package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.status.Rented;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository.RoomDao;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Hotel {
    public static void main(String[] args) {
        new RoomDao().create(new Room((short) 203, "President", 5, new Rented("Rented",
                LocalDate.of(2019, 12, 26), LocalDate.of(2020, 11, 3))));
    }
}
