package com.senlainc.git_courses.java_training.petushok_valiantsin.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.MenuController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.data.LoadData;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.logger.CustomLogger;

public class Controller {
    public static void main(String[] args) {
        CustomLogger.execute();
        LoadData.execute();
        new MenuController().run();
    }
}
