package com.senlainc.git_courses.java_training.petushok_valiantsin.injection;

import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.utility.ClassReader;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DependencyInject {
    private static final List<Class<?>> projectClasses;
    private static DependencyInject instance;

    static {
        try {
            projectClasses = ClassReader.getClasses().stream().filter(i -> i.getInterfaces().length > 0).collect(Collectors.toList());
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static DependencyInject getInstance() {
        if (instance == null) {
            instance = new DependencyInject();
        }
        return instance;
    }

    public Constructor<?> injection(Field field) throws NoSuchMethodException {
        if (field.getType().isInterface()) {
            return interfaceInjection(field);
        }
        return field.getType().getDeclaredConstructor();
    }

    private Constructor<?> interfaceInjection(Field field) throws NoSuchMethodException {
        for (Class<?> clazz : projectClasses) {
            final Class<?> clazzInterface = Arrays.stream(clazz.getInterfaces()).filter(i -> field.getType().equals(i)).findFirst().orElse(null);
            if (clazzInterface != null) {
                return clazz.getDeclaredConstructor();
            }
        }
        return null;
    }
}
