package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.exit;

import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.IAction;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.CustomEntityManager;

public class MenuExit implements IAction {

    @Override
    public void execute() {
        CustomEntityManager.closeEntityManagerFactory();
        System.exit(0);
    }
}
