package com.senlainc.git_courses.java_training.petushok_valiantsin.configuration;

import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigProperty;

import java.lang.reflect.InvocationTargetException;

@ConfigClass
public class Try {
    @ConfigProperty(configName = "try", propertyName = "TRY.FIRST_VARIABLE")
    private final Short firstVariable = null;
    @ConfigProperty(configName = "TRY")
    private String secondVariable;

    public void configTry () {
        try {
            ConfigController.getInstance().letsRock(this);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.err.println(e);
        }
        System.out.println(firstVariable + "\n" + secondVariable);
    }
}
