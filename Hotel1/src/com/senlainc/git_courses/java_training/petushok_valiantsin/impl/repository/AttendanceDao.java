package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.utility.MyList;

public class AttendanceDao implements IAttendanceDao {
    private MyList<Attendance> attendanceMyList = new MyList<>();

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
