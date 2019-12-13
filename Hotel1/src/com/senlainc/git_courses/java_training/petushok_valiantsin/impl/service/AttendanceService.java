package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository.AttendanceDao;

public class AttendanceService implements IAttendanceService {
    private IAttendanceDao attendanceDao = new AttendanceDao();

    @Override
    public void add(Attendance attendance) {
        try {
            attendanceDao.create(attendance);
        } catch (Exception e) {
            System.err.println("Wrong data.");
        }
    }

    @Override
    public void delete(int index) {
        try {
            attendanceDao.delete(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Attendance with index: " + index + " dont exists.");
        }
    }

    @Override
    public void changePrice(int index, double price) {
        Attendance attendance = new Attendance(attendanceDao.read(index));
        attendance.setPrice(price);
        attendanceDao.update(index, attendance);
    }

    @Override
    public void showList(String sortParameter) {
        System.out.println(attendanceDao.readAll());
    }
}
