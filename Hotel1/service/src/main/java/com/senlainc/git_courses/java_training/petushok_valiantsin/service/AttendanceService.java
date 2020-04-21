package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.data.MaxResult;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.CreateQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.DeleteQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.ReadQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.UpdateQueryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class AttendanceService implements IAttendanceService {

    private static final Logger LOGGER = LogManager.getLogger(AttendanceService.class);
    private final IAttendanceDao attendanceDao;

    @Autowired
    public AttendanceService(IAttendanceDao attendanceDao) {
        this.attendanceDao = attendanceDao;
    }

    @Override
    @Transactional
    public void add(String name, String section, double price) {
        try {
            attendanceDao.create(new Attendance(name, section, price));
            LOGGER.info("Add attendance in database");
        } catch (CreateQueryException e) {
            LOGGER.warn("Error while creating attendance.", e);
        }
    }

    @Override
    @Transactional
    public void delete(long index) {
        try {
            attendanceDao.delete(index);
            LOGGER.info("Delete attendance with index: {} from database", index);
        } catch (DeleteQueryException e) {
            LOGGER.warn("Error while deleting attendance.", e);
        }
    }

    @Override
    @Transactional
    public void changePrice(long index, double price) {
        try {
            final Attendance attendance = attendanceDao.read(index);
            attendance.setPrice(price);
            attendanceDao.update(attendance);
            LOGGER.info("Change attendance price");
        } catch (UpdateQueryException e) {
            LOGGER.warn("Error while updating attendance. Update operation: change price.", e);
        } catch (ReadQueryException e) {
            LOGGER.warn("Attendance with index {} don't exists.", index, e);
        }
    }

    @Override
    public List<Attendance> getAttendanceList() {
        final int maxResult = MaxResult.ATTENDANCE.getMaxResult();
        try {
            return attendanceDao.readAll(attendanceDao.readSize().intValue() - maxResult, maxResult);
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read attendance's.", e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Attendance> sort(String parameter) {
        final int maxResult = MaxResult.ATTENDANCE.getMaxResult();
        try {
            if (parameter.equals("default")) {
                return getAttendanceList();
            }
            return attendanceDao.readAll(attendanceDao.readSize().intValue() - maxResult, maxResult, parameter);
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read attendance's.", e);
        }
        return Collections.emptyList();
    }
}
