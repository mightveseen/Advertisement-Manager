package com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

public class DependencyController {
    private static final Logger LOGGER = LogManager.getLogger(DependencyController.class.getName());
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
            LOGGER.log(Level.DEBUG, String.format("Successful load class: %s", clazz.getSimpleName()));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | ClassNotFoundException e) {
            LOGGER.log(Level.WARN, String.format("Could load class: %s, reason: %s", clazz, e.toString()), e);
        }
    }

    public <T> T getClazz(Class<?> clazz) {
        return (T) DependencyService.getInstance().getInstanceClass(clazz);
    }
}
