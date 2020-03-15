package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.room;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.DependencyController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.IAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ShowNumberFreeRoom implements IAction {
    private static final Logger LOGGER = LogManager.getLogger(ShowNumberFreeRoom.class);

    @Override
    public void execute() {
        final Hotel hotel = (Hotel) DependencyController.getInstance().getClazz(Hotel.class);
        System.out.println(hotel.numFreeRoom());
        LOGGER.info("Show umber of free room");
    }
}
