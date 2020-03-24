package com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection;

import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyComponent;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class DependencyService {

    private static DependencyService instance;
    private final Map<String, Object> instanceClassMap;
    private Object instanceClass;

    private DependencyService() {
        this.instanceClassMap = new HashMap<>();
    }

    public static DependencyService getInstance() {
        if (instance == null) {
            instance = new DependencyService();
        }
        return instance;
    }

    public void setVariable(Class<?> clazz) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        if (clazz.getAnnotation(DependencyClass.class) == null) {
            throw new IllegalArgumentException("Class: " + clazz.getSimpleName() + " didn't have 'DependencyClass' annotation");
        }
        final Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        this.instanceClass = constructor.newInstance();
        constructor.setAccessible(false);
        instanceClassMap.put(clazz.getName(), instanceClass);
    }

    public void initializeConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        final List<Field> annotatedFields = Arrays.stream(instanceClass.getClass().getDeclaredFields())
                .filter(i -> !i.getType().isPrimitive() && i.isAnnotationPresent(DependencyComponent.class))
                .collect(Collectors.toList());
        for (Field field : annotatedFields) {
            final Constructor<?> constructor = DependencyInject.getInstance().injection(field);
            if (constructor.getDeclaringClass().isAnnotationPresent(DependencyClass.class) && !instanceClassMap.containsKey(constructor.getName())) {
                final Object bufInstanceClass = this.instanceClass;
                setVariable(constructor.getDeclaringClass());
                initializeConstructor();
                this.instanceClass = bufInstanceClass;
            }
            field.setAccessible(true);
            if (instanceClassMap.containsKey(constructor.getName())) {
                field.set(instanceClass, instanceClassMap.get(constructor.getName()));
                field.setAccessible(false);
                continue;
            }
            constructor.setAccessible(true);
            final Object clazz = constructor.newInstance();
            constructor.setAccessible(false);
            field.set(instanceClass, clazz);
            instanceClassMap.put(clazz.getClass().getName(), clazz);
            field.setAccessible(false);
        }
    }

    public Object getInstanceClass(Class<?> clazz) {
        return instanceClassMap.get(clazz.getName());
    }
}