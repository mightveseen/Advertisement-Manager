package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.order;

import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

public class ShowActiveOrder implements IAction {
    private String parameter;

    public ShowActiveOrder(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public void execute() {
        Hotel.getInstance().sortOrder(parameter);
    }
}
