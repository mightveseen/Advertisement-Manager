package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.room;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowRoom implements IAction {
    private static final Logger LOGGER = Logger.getLogger(Hotel.class.getSimpleName());
    private final String type;
    private final String parameter;

    public ShowRoom(String type, String parameter) {
        this.parameter = parameter;
        this.type = type;
    }

    @Override
    public void execute() {
        Hotel.getInstance().sortRoom(type, parameter).forEach(System.out::println);
        LOGGER.log(Level.INFO, "Show room list sorted by: " + parameter);
    }
}
