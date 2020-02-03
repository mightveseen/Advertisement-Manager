package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration;

public class RoomConfig extends Configuration {
    private static final String PATH = "src/resources/properties/configuration/room.properties";
    private static final String CHANGE_STATUS_KEY = "CHANGE_STATUS_PROPERTY";
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
        if (checkProperty(CHANGE_STATUS_KEY) && getValue(CHANGE_STATUS_KEY).equals("false")) {
            return false;
        }
        return CHANGE_STATUS_BASIC_VALUE;
    }
}
