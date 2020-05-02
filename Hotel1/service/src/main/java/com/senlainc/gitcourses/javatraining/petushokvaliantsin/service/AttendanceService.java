package com.senlainc.gitcourses.javatraining.petushokvaliantsin.service;

import com.senlainc.gitcourses.javatraining.petushokvaliantsin.api.repository.IAttendanceDao;
import com.senlainc.gitcourses.javatraining.petushokvaliantsin.api.service.IAttendanceService;
import com.senlainc.gitcourses.javatraining.petushokvaliantsin.model.Attendance;
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
    public boolean create(Attendance object) {
        attendanceDao.create(object);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long index) {
        attendanceDao.delete(attendanceDao.read(index));
        return true;
    }

    @Override
    @Transactional
    public boolean update(Attendance object) {
        attendanceDao.update(object);
        return true;
    }

    @Override
    @Transactional
    public void changePrice(long index, double price) {
        final Attendance attendance = attendanceDao.read(index);
        attendance.setPrice(price);
        attendanceDao.update(attendance);
    }

    @Override
    @Transactional(readOnly = true)
    public Attendance read(Long index) {
        return attendanceDao.read(index);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Attendance> readAll(int firstElement, int maxResult) {
        return attendanceDao.readAll(firstElement, maxResult);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Attendance> readAllSorted(String parameter, int firstElement, int maxResult) {
        if (parameter.equals("default")) {
            return readAll(firstElement, maxResult);
        }
        return attendanceDao.readAll(firstElement, maxResult, parameter);
    }
}
