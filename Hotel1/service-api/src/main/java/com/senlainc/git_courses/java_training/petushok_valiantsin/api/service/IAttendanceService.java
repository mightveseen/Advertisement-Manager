package com.senlainc.git_courses.java_training.petushok_valiantsin.api.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;

import java.util.List;

public interface IAttendanceService {

    void create(String name, String section, double price);

    void delete(long index);

    void changePrice(long index, double price);

    Attendance getAttendance(long index);

    List<Attendance> getAttendances();

    List<Attendance> getSortedAttendances(String parameter);
}
