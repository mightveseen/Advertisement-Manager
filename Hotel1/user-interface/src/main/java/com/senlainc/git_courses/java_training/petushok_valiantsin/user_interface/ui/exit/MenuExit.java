package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.exit;

import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.DependencyController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.IAction;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.ConnectionManager;

public class MenuExit implements IAction {
    @Override
    public void execute() {
        final ConnectionManager connectionManager = (ConnectionManager) DependencyController.getInstance().getClazz(ConnectionManager.class);
        connectionManager.closeConnection();
        System.exit(0);
    }
}
