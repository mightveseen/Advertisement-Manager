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
        for(int i = 0; i < attendanceMyList.size(); i++) {
            if(attendanceMyList.get(i).getId() == index) {
                attendanceMyList.remove(i);
                return;
            }
        }
    }

    @Override
    public void update(Attendance attendance) {
        for(int i = 0; i < attendanceMyList.size(); i++) {
            if(attendanceMyList.get(i).getId() == attendance.getId()) {
                attendanceMyList.add(i, attendance);
                return;
            }
        }
    }

    @Override
    public MyList<Attendance> readAll() {
        return attendanceMyList;
    }

    @Override
    public Attendance read(int index) {
        for(int i = 0; i < attendanceMyList.size(); i++) {
            if(attendanceMyList.get(i).getId() == index) {
                return attendanceMyList.get(i);
            }
        }
        return null;
    }

}
