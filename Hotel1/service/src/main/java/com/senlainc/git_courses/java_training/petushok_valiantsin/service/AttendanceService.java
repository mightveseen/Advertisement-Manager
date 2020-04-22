package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.data.MaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AttendanceService implements IAttendanceService {

    private final IAttendanceDao attendanceDao;

    @Autowired
    public AttendanceService(IAttendanceDao attendanceDao) {
        this.attendanceDao = attendanceDao;
    }

    @Override
    @Transactional
    public void add(String name, String section, double price) {
        attendanceDao.create(new Attendance(name, section, price));
    }

    @Override
    @Transactional
    public void delete(long index) {
        attendanceDao.delete(index);
    }

    @Override
    @Transactional
    public void changePrice(long index, double price) {
        final Attendance attendance = attendanceDao.read(index);
        attendance.setPrice(price);
        attendanceDao.update(attendance);
    }

    @Override
    public List<Attendance> getAttendanceList() {
        final int maxResult = MaxResult.ATTENDANCE.getMaxResult();
        return attendanceDao.readAll(attendanceDao.readSize().intValue() - maxResult, maxResult);
    }

    @Override
    public List<Attendance> sort(String parameter) {
        final int maxResult = MaxResult.ATTENDANCE.getMaxResult();
        if (parameter.equals("default")) {
            return getAttendanceList();
        }
        return attendanceDao.readAll(attendanceDao.readSize().intValue() - maxResult, maxResult, parameter);
    }
}
