package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration;

public class RoomConfig extends Configuration {
    private static final String PATH = "src/com/senlainc/git_courses/java_training/petushok_valiantsin/utility/configuration/res/room.properties";
    private static final String CHANGE_STATUS_KEY = "change_status_property";
    private static final boolean CHANGE_STATUS_BASIC_VALUE = true;
    private static RoomConfig instance;

    private RoomConfig() {
        super(PATH);
    }

    public static RoomConfig getInstance() {
        if (instance == null) {
            instance = new RoomConfig();
        }
        return instance;
    }

    public boolean getChangeStatus() {
        if (checkProperty(CHANGE_STATUS_KEY)) {
            return !getValue(CHANGE_STATUS_KEY).equals("false");
        }
        return CHANGE_STATUS_BASIC_VALUE;
    }
}
