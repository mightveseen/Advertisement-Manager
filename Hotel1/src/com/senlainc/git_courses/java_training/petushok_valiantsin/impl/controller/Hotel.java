package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.service.AttendanceService;

public class Hotel {
    public static void main(String[] args) {
        IAttendanceService attendanceService = new AttendanceService();
        attendanceService.add(new Attendance("First", "Private", 12.3));
        attendanceService.add(new Attendance("Second", "Bomj", 9));
        attendanceService.changePrice(0, 11.3);
        attendanceService.showList("Price");
    }
}
