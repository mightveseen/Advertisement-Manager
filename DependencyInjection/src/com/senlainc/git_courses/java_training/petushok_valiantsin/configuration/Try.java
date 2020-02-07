package com.senlainc.git_courses.java_training.petushok_valiantsin.configuration;

import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigProperty;

import java.lang.reflect.InvocationTargetException;

@ConfigClass
public class Try {
    @ConfigProperty(configName = "try", propertyName = "FIRST_VARIABLE")
    private final String firstVariable = null;
    @ConfigProperty(configName = "try", propertyName = "SECOND_VARIABLE")
    private String secondVariable;

    public void write () {
        try {
            ConfigModule.getInstance().letsRock(Try.class.getName(), this);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            System.err.println(e);
        }
        System.out.println(firstVariable + "\n" + secondVariable);
    }
}
