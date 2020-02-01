package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration;

public class RoomConfig extends Configuration {
    private static final String CHANGE_STATUS_PROPERTY = "change_status_property";
    private static RoomConfig instance;

    private RoomConfig() {
        super(CHANGE_STATUS_PROPERTY);
    }
    public static RoomConfig getInstance() {
        if(instance == null) {
            instance = new RoomConfig();
        }
        return instance;
    }

    public boolean getChangeStatusProperty() {
        return getProperty(CHANGE_STATUS_PROPERTY).equals("true");
    }
}
