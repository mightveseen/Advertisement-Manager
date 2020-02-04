package com.senlainc.git_courses.java_training.petushok_valiantsin.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.MenuController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.logger.CustomLogger;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization.LoadData;

public class Main {
    public static void main(String[] args) {
//        Conrtoller conrtoller = new Conrtoller();
        new CustomLogger();
        new LoadData();
        new MenuController().run();
    }
}
