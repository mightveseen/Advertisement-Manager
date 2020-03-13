package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.data;

import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.DependencyController;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LoadData {
    private static final Logger LOGGER = Logger.getLogger(LoadData.class);

    private LoadData() {
        throw new IllegalStateException("Utility class");
    }

    public static void execute(Class<?> clazz) {
        /* Create instance's of project classes */
        DependencyController.getInstance().setDependency(clazz);
        LOGGER.log(Level.DEBUG, "Successful initialize class: " + clazz.getSimpleName());
    }
}
