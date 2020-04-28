package com.senlainc.git_courses.java_training.petushok_valiantsin.controller;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttendanceController {

    private final IAttendanceService attendanceService;

    @Autowired
    public AttendanceController(IAttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }
}
