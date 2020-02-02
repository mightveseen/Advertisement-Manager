package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization;

import com.senlainc.git_courses.java_training.petushok_valiantsin.controller.Hotel;

public class LoadData {
    static {
        Hotel.getInstance().loadAttendance();
        Hotel.getInstance().loadRoom();
        Hotel.getInstance().loadGuest();
        Hotel.getInstance().loadOrder();
    }
}
