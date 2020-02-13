package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration;

import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.ConfigController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.configuration.annotation.ConfigProperty;

import java.util.logging.Logger;

@ConfigClass
public class LoadConfig {
    private static final Logger LOGGER = Logger.getLogger(LoadConfig.class.getName());
    @ConfigProperty(configName = "Load")
    private static String ATTENDANCE_DAO_PATH;
    @ConfigProperty(configName = "Load")
    private static String GUEST_DAO_PATH;
    @ConfigProperty(configName = "Load")
    private static String ROOM_DAO_PATH;
    @ConfigProperty(configName = "Load")
    private static String ORDER_DAO_PATH;

    private LoadConfig() {
        ConfigController.getInstance().letsRock(LoadConfig.class);
    }

    public String getAttendanceDaoPath() {
        return ATTENDANCE_DAO_PATH;
    }

    public String getGuestDaoPath() {
        return GUEST_DAO_PATH;
    }

    public String getRoomDaoPath() {
        return ROOM_DAO_PATH;
    }

    public String getOrderDaoPath() {
        return ORDER_DAO_PATH;
    }
}
