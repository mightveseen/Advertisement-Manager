package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.room;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowNumberFreeRoom implements IAction {
    private static final Logger LOGGER = Logger.getLogger(Hotel.class.getSimpleName());

    @Override
    public void execute() {
        System.out.println(Hotel.getInstance().numFreeRoom());
        LOGGER.log(Level.INFO, "Show umber of free room");
    }
}
