package com.senlainc.git_courses.java_training.petushok_valiantsin.injection;

import java.lang.reflect.InvocationTargetException;

public class DependencyController {
    private static DependencyController instance;

    public static DependencyController getInstance() {
        if (instance == null) {
            instance = new DependencyController();
        }
        return instance;
    }

    public void lestRock(Class<?> object) {
        try {
            DependencyService.getInstance().setVariable(object);
            DependencyService.getInstance().initializeConstructor();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
