package com.senlainc.git_courses.java_training.petushok_valiantsin.room;

import com.senlainc.git_courses.java_training.petushok_valiantsin.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.IAction;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.DependencyController;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowNumberFreeRoom implements IAction {
    private static final Logger LOGGER = Logger.getLogger(ShowNumberFreeRoom.class.getSimpleName());

    @Override
    public void execute() {
        final Hotel hotel = DependencyController.getInstance().getClazz(Hotel.class);
        System.out.println(hotel.numFreeRoom());
        LOGGER.log(Level.INFO, "Show umber of free room");
    }
}
