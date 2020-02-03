package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration;

public class GuestConfig extends Configuration {
    private static final String PATH = "src/resources/properties/configuration/guest.properties";
    private static final String GUEST_LIMIT_KEY = "GUEST_STORY_LIMIT";
    private static final int GUEST_LIMIT_BASIC_VALUE = 50;
    private static GuestConfig instance;

    private GuestConfig() {
        super(PATH);
    }

    public static GuestConfig getInstance() {
        if (instance == null) {
            instance = new GuestConfig();
        }
        return instance;
    }

    public int getGuestLimit() {
        if (checkProperty(GUEST_LIMIT_KEY)) {
            return Integer.parseInt(getValue(GUEST_LIMIT_KEY));
        }
        return GUEST_LIMIT_BASIC_VALUE;
    }
}
