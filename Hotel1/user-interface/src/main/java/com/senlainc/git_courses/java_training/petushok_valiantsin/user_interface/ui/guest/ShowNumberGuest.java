package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.guest;

import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.ApplicationContextHolder;
import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.IAction;

public class ShowNumberGuest implements IAction {

    @Override
    public void execute() {
        final Hotel hotel = ApplicationContextHolder.getBean(Hotel.class);
        System.out.println(hotel.numGuest());
    }
}
