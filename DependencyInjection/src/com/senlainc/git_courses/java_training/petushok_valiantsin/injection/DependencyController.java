package com.senlainc.git_courses.java_training.petushok_valiantsin.injection;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DependencyController {
    private static DependencyController instance;
    private static final Logger LOGGER = Logger.getLogger(DependencyController.class.getName());
    private static List<Class<?>> projectClasses;

    private DependencyController() {
//        try {
//            projectClasses = ClassReader.getClasses().stream().filter(i -> i.isAnnotationPresent(DependencyClass.class)).collect(Collectors.toList());
//        } catch (ClassNotFoundException | IOException e) {
//            LOGGER.log(Level.WARNING, e.getMessage(), e);
//        }
    }

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
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | ClassNotFoundException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    public <T> T getClazz(Class<?> clazz) {
        return (T) DependencyService.getInstance().getInstanceClass(clazz);
    }
}
