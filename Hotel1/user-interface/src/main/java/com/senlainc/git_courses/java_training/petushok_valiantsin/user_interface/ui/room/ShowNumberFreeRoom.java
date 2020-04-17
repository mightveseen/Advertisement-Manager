package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.room;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.IAction;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.di.ApplicationContextHolder;


public class ShowNumberFreeRoom implements IAction {

    @Override
    public void execute() {
        final Hotel hotel = ApplicationContextHolder.getBean(Hotel.class);
        System.out.println(hotel.numFreeRoom());
    }
}
