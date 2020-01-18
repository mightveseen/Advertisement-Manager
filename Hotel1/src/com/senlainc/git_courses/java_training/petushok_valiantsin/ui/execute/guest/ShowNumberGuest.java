package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.execute.guest;

import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

public class ShowNumberGuest implements IAction {
    @Override
    public void execute() {
        Hotel.getInstance().numGuest();
    }
}
