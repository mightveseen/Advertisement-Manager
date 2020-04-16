package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.room;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.IAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class ShowRoom implements IAction {

    private static final Logger LOGGER = LogManager.getLogger(ShowRoom.class);
    private final String type;
    private final String parameter;
    @Autowired
    private Hotel hotel;

    public ShowRoom(String type, String parameter) {
        this.parameter = parameter;
        this.type = type;
    }

    @Override
    public void execute() {
        hotel.sortRoom(type, parameter).forEach(System.out::println);
        LOGGER.info("Show room list sorted by: {}", parameter);
    }
}
