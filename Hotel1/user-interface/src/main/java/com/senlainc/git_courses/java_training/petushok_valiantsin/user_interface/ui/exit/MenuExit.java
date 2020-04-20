package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.exit;

import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.IAction;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ExitException;

public class MenuExit implements IAction {

    @Override
    public void execute() {
        throw new ExitException("Exit from project");
    }
}
