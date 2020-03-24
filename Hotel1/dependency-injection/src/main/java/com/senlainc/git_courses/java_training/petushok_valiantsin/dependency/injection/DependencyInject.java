package com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection;

import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.utility.ClassReader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class DependencyInject {

    private static DependencyInject instance;
    private final List<Class<?>> projectClasses;
    private final Map<String, Constructor<?>> interfaceConstructorMap;

    private DependencyInject() {
        this.interfaceConstructorMap = new HashMap<>();
        projectClasses = Objects.requireNonNull(ClassReader.getAllClasses("")).stream()
                .filter(i -> !i.isInterface())
                .filter(i -> i.getInterfaces().length > 0)
                .filter(i -> !i.getName().contains("user_interface"))
                .collect(Collectors.toList());
    }

    public static DependencyInject getInstance() {
        if (instance == null) {
            instance = new DependencyInject();
        }
        return instance;
    }

    public Constructor<?> injection(Field field) throws NoSuchMethodException, ClassNotFoundException {
        if (field.getType().isInterface()) {
            return interfaceInjection(field);
        }
        return field.getType().getDeclaredConstructor();
    }

    private Constructor<?> interfaceInjection(Field field) throws NoSuchMethodException, ClassNotFoundException {
        if (interfaceConstructorMap.containsKey(field.getType().getName())) {
            return interfaceConstructorMap.get(field.getType().getName());
        }
        final Class<?> clazz = multiImplementation(field);
        interfaceConstructorMap.put(field.getType().getName(), clazz.getDeclaredConstructor());
        return clazz.getDeclaredConstructor();
    }

    private Class<?> multiImplementation(Field field) throws ClassNotFoundException {
        final List<Class<?>> interfaceClass = new ArrayList<>();
        for (Class<?> clazz : projectClasses) {
            if (Arrays.stream(clazz.getInterfaces()).anyMatch(i -> i.equals(field.getType()))) {
                interfaceClass.add(clazz);
            }
        }
        if (interfaceClass.size() == 1) {
            return interfaceClass.get(0);
        } else {
            return interfaceClass.stream()
                    .filter(i -> i.isAnnotationPresent(DependencyPrimary.class))
                    .findFirst().orElseThrow(ClassNotFoundException::new);
        }
    }
}