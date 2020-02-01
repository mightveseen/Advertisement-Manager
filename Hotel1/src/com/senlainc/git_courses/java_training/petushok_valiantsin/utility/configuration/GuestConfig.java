package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration;

public class GuestConfig extends Configuration{
    private static GuestConfig instance;
    private static final String PATH = "src/com/senlainc/git_courses/java_training/petushok_valiantsin/utility/configuration/res/guest.properties";
    private static final String GUEST_STORY_LIMIT_PROPERTY = "guest_story_limit";

    private GuestConfig() {
        super(PATH);
    }
    public static GuestConfig getInstance() {
        if(instance == null) {
            instance = new GuestConfig();
        }
        return instance;
    }

    public Integer getGuestLimit() {
        if(checkProperty(GUEST_STORY_LIMIT_PROPERTY)) {
            return Integer.parseInt(getValue(GUEST_STORY_LIMIT_PROPERTY));
        }
        return 50;
    }
}
