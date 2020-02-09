package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.DependencyController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.service.AttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.service.GuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.service.OrderService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.service.RoomService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration.GuestConfig;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration.LoadConfig;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration.RoomConfig;

public class LoadData {
    static {
        DependencyController.getInstance().lestRock(LoadConfig.class);
        DependencyController.getInstance().lestRock(RoomConfig.class);
        DependencyController.getInstance().lestRock(GuestConfig.class);
        DependencyController.getInstance().lestRock(Serialization.class);
        DependencyController.getInstance().lestRock(AttendanceService.class);
        DependencyController.getInstance().lestRock(RoomService.class);
        DependencyController.getInstance().lestRock(GuestService.class);
        DependencyController.getInstance().lestRock(OrderService.class);
        DependencyController.getInstance().lestRock(Hotel.class);
        Hotel.getInstance().loadAttendance();
        Hotel.getInstance().loadRoom();
        Hotel.getInstance().loadGuest();
        Hotel.getInstance().loadOrder();
    }
}
