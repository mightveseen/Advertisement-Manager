package com.senlainc.git_courses.java_training.petushok_valiantsin.api.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.MyList;

public interface IAttendanceService {
    void add(Attendance attendance);

    void delete(int index);

    void changePrice(int index, double price);

    double getPrice(int index);

    Attendance get(int index);

    MyList<Attendance> sort(String parameter);
}
