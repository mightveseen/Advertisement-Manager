package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface;

import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.ApplicationContextHolder;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.MenuController;

public class Start {

    public static void main(String[] args) {
        ApplicationContextHolder.loadConfig();
        new MenuController().run();
    }
}
