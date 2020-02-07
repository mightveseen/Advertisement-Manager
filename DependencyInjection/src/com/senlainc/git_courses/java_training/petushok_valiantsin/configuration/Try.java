package com.senlainc.git_courses.java_training.petushok_valiantsin.configuration;

import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigProperty;

import java.lang.reflect.InvocationTargetException;

@ConfigClass
public class Try {
    @ConfigProperty(configName = "try", propertyName = "FIRST_VARIABLE")
    private static String firstVariable;
    @ConfigProperty(configName = "try", propertyName = "SECOND_VARIABLE")
    private static String secondVariable;

    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.Try");
            ConfigModule.getInstance().letsRock(clazz);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.err.println(e);
        }
        System.out.println(firstVariable + "\n" + secondVariable);
    }
}
