package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration;

public class LoadConfig extends Configuration {
    private static final String ATTENDANCE_DAO_PATH_PROPERTY = "ATTENDANCE_DAO_PATH";
    private static final String GUEST_DAO_PATH_PROPERTY = "GUEST_DAO_PATH";
    private static final String ROOM_DAO_PATH_PROPERTY = "ROOM_DAO_PATH";
    private static final String ORDER_DAO_PATH_PROPERTY = "ORDER_DAO_PATH";
    private static final String PATH = "resources/properties/load.properties";
    private static LoadConfig instance;

    private LoadConfig() {
        super(PATH);
    }

    public static LoadConfig getInstance() {
        if (instance == null) {
            instance = new LoadConfig();
        }
        return instance;
    }

    public String getAttendanceDaoPathProperty() {
        return getValue(ATTENDANCE_DAO_PATH_PROPERTY);
    }

    public String getGuestDaoPathProperty() {
        return getValue(GUEST_DAO_PATH_PROPERTY);
    }

    public String getRoomDaoPathProperty() {
        return getValue(ROOM_DAO_PATH_PROPERTY);
    }

    public String getOrderDaoPathProperty() {
        return getValue(ORDER_DAO_PATH_PROPERTY);
    }
}
