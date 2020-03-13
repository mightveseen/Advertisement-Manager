package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.guest;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.DependencyController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.IAction;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowNumberGuest implements IAction {
    private static final Logger LOGGER = LogManager.getLogger(ShowNumberGuest.class.getName());

    @Override
    public void execute() {
        final Hotel hotel = (Hotel) DependencyController.getInstance().getClazz(Hotel.class);
        System.out.println(hotel.numGuest());
        LOGGER.log(Level.INFO, "Show number of guest");
    }
}
