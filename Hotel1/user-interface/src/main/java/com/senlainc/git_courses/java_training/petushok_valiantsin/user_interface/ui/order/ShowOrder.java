package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.order;

import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.ApplicationContextHolder;
import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.IAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowOrder implements IAction {

    private static final Logger LOGGER = LogManager.getLogger(ShowOrder.class);
    private final String parameter;

    public ShowOrder(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public void execute() {
        final Hotel hotel = ApplicationContextHolder.getBean(Hotel.class);
        hotel.sortOrder(parameter).forEach(System.out::println);
        LOGGER.info("Show order list sorted by: {}", parameter);
    }
}
