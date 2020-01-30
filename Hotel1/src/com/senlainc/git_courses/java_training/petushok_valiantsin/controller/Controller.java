package com.senlainc.git_courses.java_training.petushok_valiantsin.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.MenuController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.logger.CustomLogger;

public class Controller {

    public static void main(String[] args) {
        new CustomLogger();
        new MenuController().run();
    }
}
