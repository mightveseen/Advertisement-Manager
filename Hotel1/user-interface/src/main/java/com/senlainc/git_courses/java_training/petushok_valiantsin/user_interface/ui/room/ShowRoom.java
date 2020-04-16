package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.room;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.IAction;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.di.ApplicationContextHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowRoom implements IAction {

    private static final Logger LOGGER = LogManager.getLogger(ShowRoom.class);
    private final String type;
    private final String parameter;

    public ShowRoom(String type, String parameter) {
        this.parameter = parameter;
        this.type = type;
    }

    @Override
    public void execute() {
        final Hotel hotel = (Hotel) ApplicationContextHolder.getBean(Hotel.class);
        hotel.sortRoom(type, parameter).forEach(System.out::println);
        LOGGER.info("Show room list sorted by: {}", parameter);
    }
}
