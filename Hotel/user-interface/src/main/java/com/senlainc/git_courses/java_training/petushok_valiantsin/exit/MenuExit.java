package com.senlainc.git_courses.java_training.petushok_valiantsin.exit;

import com.senlainc.git_courses.java_training.petushok_valiantsin.IAction;
import com.senlainc.git_courses.java_training.petushok_valiantsin.base_conection.ConnectionManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.DependencyController;

public class MenuExit implements IAction {
    @Override
    public void execute() {
        final ConnectionManager connectionManager = DependencyController.getInstance().getClazz(ConnectionManager.class);
        connectionManager.closeConnection();
        System.exit(0);
    }
}
