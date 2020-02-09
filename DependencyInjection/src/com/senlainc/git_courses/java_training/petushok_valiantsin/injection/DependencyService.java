package com.senlainc.git_courses.java_training.petushok_valiantsin.injection;

import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class DependencyService {
    private final Class<?> dependencyClass;
    private final Object instanceClass;

    public DependencyService(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        this.dependencyClass = clazz;
        this.instanceClass = dependencyClass.newInstance();
    }

    public Object initializeConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for (Field field : dependencyClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(DependencyComponent.class) && !field.getType().isPrimitive()) {
                final Constructor<?> constructor = DependencyInject.getInstance().injection(field);
                if (constructor != null) {
                    field.setAccessible(true);
                    field.set(instanceClass, constructor.newInstance());
                    field.setAccessible(false);
                }
            }
        }
        return instanceClass;
    }
}
