package com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.utility.MyList;

public interface IAttendanceDao {
    void create(Attendance attendance);

    void delete(int index);

    void update(int index, Attendance attendance);

    MyList<Attendance> readAll();

    Attendance read(int index);

//    List<Attendance> sortByPrice();

//    List<Attendance> sortBySection();
}
