package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.MenuController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.data.LoadData;

public class Start {

    public static void main(String[] args) {
        LoadData.execute(Hotel.class);
        new MenuController().run();
    }
}
