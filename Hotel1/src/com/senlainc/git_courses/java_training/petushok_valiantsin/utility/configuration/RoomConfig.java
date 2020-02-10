package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration;

import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.ConfigController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigProperty;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;

import java.util.logging.Level;
import java.util.logging.Logger;

@ConfigClass
@DependencyClass
public class RoomConfig {
    private static final Logger LOGGER = Logger.getLogger(RoomConfig.class.getName());
    private static final boolean CHANGE_STATUS_BASIC_VALUE = true;
    @ConfigProperty(configName = "Room")
    private static boolean CHANGE_STATUS_VALUE;
    @DependencyComponent
    private static RoomConfig instance;

    private RoomConfig() {
        try {
            ConfigController.getInstance().letsRock(RoomConfig.class);
        } catch (RuntimeException e) {
            LOGGER.log(Level.WARNING, "Could load program resources.properties from file: " + e.toString(), e);
        }
    }

    public static RoomConfig getInstance() {
        return instance;
    }

    public boolean getChangeStatus() {
        if (String.valueOf(CHANGE_STATUS_VALUE).equals("true") || String.valueOf(CHANGE_STATUS_VALUE).equals("false")) {
            return CHANGE_STATUS_VALUE;
        }
        return CHANGE_STATUS_BASIC_VALUE;
    }
}
