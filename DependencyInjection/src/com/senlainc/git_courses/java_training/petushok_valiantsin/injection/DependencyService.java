package com.senlainc.git_courses.java_training.petushok_valiantsin.injection;

import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DependencyService {
    private static DependencyService instance;
    private Class<?> dependencyClass;
    private Object instanceClass;
    private static final Map<String, Object> instanceClassMap = new HashMap<>();

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
        instanceClassMap.put(clazz.getName(), instanceClass);
    }

    public void initializeConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final List<Field> declaredFields = Arrays.stream(dependencyClass.getDeclaredFields()).filter(i -> !i.getType().isPrimitive() && i.isAnnotationPresent(DependencyComponent.class)).collect(Collectors.toList());
        for (Field field : declaredFields) {
            final Constructor<?> constructor = DependencyInject.getInstance().injection(field);
            field.setAccessible(true);
            if(!instanceClassMap.containsKey(constructor.getName())) {
                constructor.setAccessible(true);
                if(instanceClass.getClass().getName().equals(field.getType().getName())) {
                    field.set(instanceClass, instanceClass);
                } else {
                    field.set(instanceClass, constructor.newInstance());
                }
                constructor.setAccessible(false);
            } else {
                field.set(instanceClass, instanceClassMap.get(constructor.getName()));
            }
            field.setAccessible(false);
        }
    }

    public Object getInstanceClass(Class<?> clazz) {
        return instanceClassMap.get(clazz.getName());
    }
}
