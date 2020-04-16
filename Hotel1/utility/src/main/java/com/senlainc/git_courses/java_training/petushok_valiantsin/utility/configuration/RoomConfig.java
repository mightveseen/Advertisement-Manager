package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration;

import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.configuration.ConfigController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.configuration.annotation.ConfigClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.configuration.annotation.ConfigProperty;

@ConfigClass
public final class RoomConfig {

    private static final boolean CHANGE_STATUS_BASIC_VALUE = true;
    @ConfigProperty(configName = "Room")
    private static boolean CHANGE_STATUS_VALUE;

    static {
        ConfigController.getInstance().setConfig(RoomConfig.class);
    }

    private RoomConfig() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean getChangeStatus() {
        if (String.valueOf(CHANGE_STATUS_VALUE).equals("true") || String.valueOf(CHANGE_STATUS_VALUE).equals("false")) {
            return CHANGE_STATUS_VALUE;
        }
        return CHANGE_STATUS_BASIC_VALUE;
    }
}
