package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.DependencyController;
import com.senlainc.git_courses.java_training.petushok_valiantsin.service.AttendanceService;

public class LoadData {
    static {
        DependencyController.getInstance().lestRock(AttendanceService.class);
        Hotel.getInstance().loadAttendance();
        Hotel.getInstance().loadRoom();
        Hotel.getInstance().loadGuest();
        Hotel.getInstance().loadOrder();
    }
}
