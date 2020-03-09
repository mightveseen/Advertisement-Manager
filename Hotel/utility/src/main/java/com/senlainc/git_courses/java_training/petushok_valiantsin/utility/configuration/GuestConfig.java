package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration;

import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.configuration.ConfigController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.configuration.annotation.ConfigClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.configuration.annotation.ConfigProperty;

@ConfigClass
public class GuestConfig {
    private static final int GUEST_LIMIT_BASIC_VALUE = 50;
    @ConfigProperty(configName = "Guest")
    private static int GUEST_LIMIT_VALUE;

    private GuestConfig() {
        ConfigController.getInstance().setConfig(GuestConfig.class);
    }

    public int getGuestLimit() {
        if (String.valueOf(GUEST_LIMIT_VALUE).equals("null") || GUEST_LIMIT_VALUE == 0) {
            return GUEST_LIMIT_BASIC_VALUE;
        }
        return GUEST_LIMIT_VALUE;
    }
}
