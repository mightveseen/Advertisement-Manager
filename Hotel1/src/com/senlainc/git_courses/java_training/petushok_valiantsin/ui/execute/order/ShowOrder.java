package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.order;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

public class ShowOrder implements IAction {
    private final String parameter;

    public ShowOrder(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public void execute() {
        if (parameter.equals("default")) {
            Hotel.getInstance().showOrder();
            return;
        }
        Hotel.getInstance().sortOrder(parameter);
    }
}
