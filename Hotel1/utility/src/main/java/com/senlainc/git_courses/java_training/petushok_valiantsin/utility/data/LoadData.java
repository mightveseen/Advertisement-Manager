package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.data;

import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.DependencyController;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadData {
    private static final Logger LOGGER = LogManager.getLogger(LoadData.class.getName());

    private LoadData() {
        throw new IllegalStateException("Utility class");
    }

    public static void execute(Class<?> clazz) {
        /* Create instance's of project classes */
        DependencyController.getInstance().setDependency(clazz);
        LOGGER.log(Level.INFO, String.format("Successful initialize class: %s", clazz.getSimpleName()));
    }
}
