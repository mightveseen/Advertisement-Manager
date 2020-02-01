package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration;

public class GuestConfig extends Configuration{
    private static final String GUEST_STORY_LIMIT_PROPERTY = "guest_story_limit";
    private static GuestConfig instance;

    private GuestConfig() {
        super(GUEST_STORY_LIMIT_PROPERTY);
    }
    public static GuestConfig getInstance() {
        if(instance == null) {
            instance = new GuestConfig();
        }
        return instance;
    }

    public int getGuestStoryLimitProperty() {
        return Integer.parseInt(getProperty(GUEST_STORY_LIMIT_PROPERTY));
    }
}
