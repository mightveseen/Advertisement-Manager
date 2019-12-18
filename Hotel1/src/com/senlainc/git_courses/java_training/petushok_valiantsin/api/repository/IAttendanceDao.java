package com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.MyList;

public interface IAttendanceDao {
    void create(Attendance attendance);

    void delete(int index);

    void update(Attendance attendance);

    MyList<Attendance> readAll();

    Attendance read(int index);
}
