package com.senlainc.git_courses.java_training.petushok_valiantsin.injection;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DependencyController {
    private static final Logger LOGGER = Logger.getLogger(DependencyController.class.getName());
    private static DependencyController instance;

    public static DependencyController getInstance() {
        if (instance == null) {
            instance = new DependencyController();
        }
        return instance;
    }

    public void setDependency(Class<?> clazz) {
        try {
            DependencyService.getInstance().setVariable(clazz);
            DependencyService.getInstance().initializeConstructor();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | ClassNotFoundException e) {
            LOGGER.log(Level.WARNING, "Could load class: " + clazz + " reason: " + e.toString(), e);
        }
    }

    public <T> T getClazz(Class<?> clazz) {
        return (T) DependencyService.getInstance().getInstanceClass(clazz);
    }
}
