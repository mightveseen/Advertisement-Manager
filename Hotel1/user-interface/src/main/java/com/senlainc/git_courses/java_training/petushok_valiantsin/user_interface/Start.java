package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface;

import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.MenuController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.di.ApplicationContextHolder;

public class Start {

    public static void main(String[] args) {
        ApplicationContextHolder.loadConfig();
        new MenuController().run();
    }
}
