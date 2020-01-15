package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;

import java.util.ArrayList;
import java.util.List;

public class AttendanceDao implements IAttendanceDao {
    private final List<Attendance> attendanceList = new ArrayList<>();

    @Override
    public void create(Attendance attendance) {
        attendanceList.add(attendance);
    }

    @Override
    public void delete(int index) {
        try {
            attendanceList.remove(attendanceList.stream().filter(i -> i.getId() == index).findFirst().orElse(null));
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    public void update(Attendance attendance) {
        try {
            attendanceList.add(attendanceList.stream().filter(i -> i.getId() == attendance.getId()).findFirst().orElse(null).getId(), attendance);
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    public List<Attendance> readAll() {
        return attendanceList;
    }

    @Override
    public Attendance read(int index) {
        return attendanceList.stream().filter(i -> i.getId() == index).findFirst().orElse(null);
    }
}
