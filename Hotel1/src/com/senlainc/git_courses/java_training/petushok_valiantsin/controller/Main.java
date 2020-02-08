package com.senlainc.git_courses.java_training.petushok_valiantsin.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.logger.CustomLogger;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization.LoadData;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        new CustomLogger();
        new LoadData();
        Hotel.getInstance().addGuest("fff", "ffff", LocalDate.now(), "ddd");
    }
}
