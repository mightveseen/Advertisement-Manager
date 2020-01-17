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
        attendanceList.remove(attendanceList.stream().filter(i -> i.getId() == index).findFirst().orElseThrow(NullPointerException::new));
    }

    @Override
    public void update(Attendance attendance) {
        attendanceList.set(attendanceList.indexOf(attendance), attendance);
    }

    @Override
    public List<Attendance> readAll() {
        return attendanceList;
    }

    @Override
    public Attendance read(int index) {
        return attendanceList.stream().filter(i -> i.getId() == index).findFirst().orElseThrow(NullPointerException::new);
    }
}
