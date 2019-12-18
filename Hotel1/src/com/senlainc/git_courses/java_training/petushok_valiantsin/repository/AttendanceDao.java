package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.MyList;

public class AttendanceDao implements IAttendanceDao {
    private final MyList<Attendance> attendanceMyList = new MyList<>();

    @Override
    public void create(Attendance attendance) {
        attendanceMyList.add(attendance);
    }

    @Override
    public void delete(int index) {
        attendanceMyList.remove(index);
    }

    @Override
    public void update(int index, Attendance attendance) {
        attendanceMyList.add(index, attendance);
    }

    @Override
    public MyList<Attendance> readAll() {
        return attendanceMyList;
    }

    @Override
    public Attendance read(int index) {
        return attendanceMyList.get(index);
    }

}
