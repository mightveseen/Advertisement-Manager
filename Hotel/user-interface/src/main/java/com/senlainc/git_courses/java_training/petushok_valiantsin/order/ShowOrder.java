package com.senlainc.git_courses.java_training.petushok_valiantsin.order;

import com.senlainc.git_courses.java_training.petushok_valiantsin.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.IAction;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.DependencyController;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowOrder implements IAction {
    private static final Logger LOGGER = Logger.getLogger(ShowOrder.class.getSimpleName());
    private final String parameter;

    public ShowOrder(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public void execute() {
        final Hotel hotel = DependencyController.getInstance().getClazz(Hotel.class);
        hotel.sortOrder(parameter).forEach(System.out::println);
        LOGGER.log(Level.INFO, "Show order list sorted by: " + parameter);
    }
}
