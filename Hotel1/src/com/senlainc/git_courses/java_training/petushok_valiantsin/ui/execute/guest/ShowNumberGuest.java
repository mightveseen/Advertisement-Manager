package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.guest;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowNumberGuest implements IAction {
    private static final Logger LOGGER = Logger.getLogger(Hotel.class.getSimpleName());

    @Override
    public void execute() {
        System.out.println(Hotel.getInstance().numGuest());
        LOGGER.log(Level.INFO, "Show number of guest");
    }
}
