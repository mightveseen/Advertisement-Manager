package com.senlainc.git_courses.java_training.petushok_valiantsin.injection;

import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DependencyService {
    private static DependencyService instance;
    private Class<?> dependencyClass;
    private Object instanceClass;

    public static DependencyService getInstance() {
        if (instance == null) {
            instance = new DependencyService();
        }
        return instance;
    }

    public void setVariable(Class<?> clazz) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        this.dependencyClass = clazz;
        final Constructor<?> constructor = dependencyClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        this.instanceClass = constructor.newInstance();
        constructor.setAccessible(false);
    }

    public Object initializeConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final List<Field> declaredFields = Arrays.stream(dependencyClass.getDeclaredFields()).filter(i -> !i.getType().isPrimitive() && i.isAnnotationPresent(DependencyComponent.class)).collect(Collectors.toList());
        for (Field field : declaredFields) {
            final Constructor<?> constructor = DependencyInject.getInstance().injection(field);
            if (constructor != null) {
                field.setAccessible(true);
                constructor.setAccessible(true);
                field.set(dependencyClass, constructor.newInstance());
                constructor.setAccessible(false);
                field.setAccessible(false);
            }
        }
        return instanceClass;
    }
}
