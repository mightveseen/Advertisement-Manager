package com.senlainc.git_courses.java_training.petushok_valiantsin.api.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;

import java.util.List;

public interface IAttendanceService {
    void add(String name, String section, double price);

    void delete(int index);

    void changePrice(int index, double price);

    void showAttendance();

    double getPrice(int index);

    Attendance get(int index);

    List<Attendance> sort(String parameter);
}
