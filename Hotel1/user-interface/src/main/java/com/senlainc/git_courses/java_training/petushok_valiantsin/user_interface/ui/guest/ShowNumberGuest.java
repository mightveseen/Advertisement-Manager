package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.guest;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.IAction;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.di.ApplicationContextHolder;

public class ShowNumberGuest implements IAction {

    @Override
    public void execute() {
        final Hotel hotel = (Hotel) ApplicationContextHolder.getBean(Hotel.class);
        System.out.println(hotel.numGuest());
    }
}
