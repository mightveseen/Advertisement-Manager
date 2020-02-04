package com.senlainc.git_courses.java_training.petushok_valiantsin.ui.exit;

import com.senlainc.git_courses.java_training.petushok_valiantsin.ui.IAction;

public class MenuExit implements IAction {
    @Override
    public void execute() {
        System.exit(0);
    }
}
