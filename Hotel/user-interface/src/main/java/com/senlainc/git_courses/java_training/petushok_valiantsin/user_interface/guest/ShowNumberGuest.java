package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.guest;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.DependencyController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.IAction;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowNumberGuest implements IAction {
    private static final Logger LOGGER = Logger.getLogger(ShowNumberGuest.class.getSimpleName());

    @Override
    public void execute() {
        final Hotel hotel = DependencyController.getInstance().getClazz(Hotel.class);
        System.out.println(hotel.numGuest());
        LOGGER.log(Level.INFO, "Show number of guest");
    }
}
