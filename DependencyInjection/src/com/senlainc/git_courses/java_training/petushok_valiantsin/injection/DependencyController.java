package com.senlainc.git_courses.java_training.petushok_valiantsin.injection;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class DependencyController {
    private static DependencyController instance;
    private static Map<String, Object> instanceMap = new HashMap<>();

    public static DependencyController getInstance() {
        if (instance == null) {
            instance = new DependencyController();
        }
        return instance;
    }

    public void lestRock(Class<?> object) {
        try {
            Object instanceClass = new DependencyService(object).initializeConstructor();
            instanceMap.put(object.getSimpleName(), instanceClass);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
