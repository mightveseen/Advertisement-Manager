package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration;


import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.ConfigController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigProperty;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ConfigClass
public class GuestConfig {
    @ConfigProperty(configName = "Guest")
    private static int GUEST_LIMIT_VALUE;
    private static final int GUEST_LIMIT_BASIC_VALUE = 50;
    private static GuestConfig instance;
    private static final Logger LOGGER = Logger.getLogger(Configuration.class.getName());

    private GuestConfig() {
        try {
            ConfigController.getInstance().letsRock(this);
        } catch (InvocationTargetException | NoSuchMethodException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Could load program resources.properties from file: " + e.toString(), e);
        }
    }

    public static GuestConfig getInstance() {
        if (instance == null) {
            instance = new GuestConfig();
        }
        return instance;
    }

    public int getGuestLimit() {
        if (String.valueOf(GUEST_LIMIT_VALUE).equals("null") || GUEST_LIMIT_VALUE == 0) {
            return GUEST_LIMIT_BASIC_VALUE;
        }
        return GUEST_LIMIT_VALUE;
    }
}
