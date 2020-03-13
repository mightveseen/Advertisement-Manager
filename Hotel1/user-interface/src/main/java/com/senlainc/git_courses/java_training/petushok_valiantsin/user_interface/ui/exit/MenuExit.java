package com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.exit;

import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.DependencyController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.user_interface.ui.IAction;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.ConnectionManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MenuExit implements IAction {
    private static final Logger LOGGER = LogManager.getLogger(MenuExit.class.getName());

    @Override
    public void execute() {
        final ConnectionManager connectionManager = (ConnectionManager) DependencyController.getInstance().getClazz(ConnectionManager.class);
        connectionManager.closeConnection();
        LOGGER.log(Level.DEBUG, "Close Database connection");
        System.exit(0);
    }
}
