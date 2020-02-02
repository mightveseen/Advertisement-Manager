package com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;

import java.util.List;

public interface IAttendanceDao {
    void create(Attendance attendance);

    void delete(int index);

    void update(Attendance attendance);

    List<Attendance> readAll();

    Attendance read(int index);

    void setAll();
}
