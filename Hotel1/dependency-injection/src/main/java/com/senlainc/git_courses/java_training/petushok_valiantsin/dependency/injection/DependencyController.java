package com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

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
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | IllegalStateException | IllegalArgumentException | ClassNotFoundException e) {
            LOGGER.warn(MessageFormat.format("Could load class: {0}, reason: {1}", clazz, e.getMessage()), e);
        }
    }

    public Object getClazz(Class<?> clazz) {
        return DependencyService.getInstance().getInstanceClass(clazz);
    }
}
