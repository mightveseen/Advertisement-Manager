package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AttendanceService implements IAttendanceService {
    private final IAttendanceDao attendanceDao;
    private final Comparator<Attendance> SORT_BY_SECTION = Comparator.comparing(Attendance::getSection);
    private final Comparator<Attendance> SORT_BY_PRICE = Comparator.comparing(attendance -> String.valueOf(attendance.getPrice()));

    public AttendanceService(IAttendanceDao attendanceDao) {
        this.attendanceDao = attendanceDao;
    }

    @Override
    public void add(String name, String section, double price) {
        attendanceDao.create(new Attendance(name, section, price));
    }

    @Override
    public void delete(int index) {
        try {
            attendanceDao.delete(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Attendance with index: " + index + " dont exists.", e);
        }
    }

    @Override
    public double getPrice(int index) {
        try {
            return attendanceDao.read(index).getPrice();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Attendance with index: " + index + " dont exists.", e);
        }
    }

    @Override
    public Attendance get(int index) {
        try {
            return attendanceDao.read(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Attendance with index: " + index + " dont exists.", e);
        }
    }

    @Override
    public void changePrice(int index, double price) {
        try {
            Attendance attendance = attendanceDao.read(index);
            attendance.setPrice(price);
            attendanceDao.update(attendance);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Attendance with index: " + index + " dont exists.", e);
        }
    }

    @Override
    public List<String> showAttendance() {
        return createStringList(attendanceDao.readAll());
    }

    @Override
    public List<Attendance> sort(String parameter) {
        List<Attendance> myList = attendanceDao.readAll();
        switch (parameter) {
            case "section":
                sortBySection(myList);
                break;
            case "price":
                sortByPrice(myList);
                break;
        }
        return myList;
    }

    private void sortBySection(List<Attendance> myList) {
        myList.sort(SORT_BY_SECTION);
    }

    private void sortByPrice(List<Attendance> myList) {
        myList.sort(SORT_BY_PRICE);
    }

    private List<String> createStringList(List<Attendance> attendanceList) {
        List<String> stringList = new ArrayList<>();
        for(Attendance attendance : attendanceList) {
            stringList.add(attendance.toString());
        }
        return stringList;
    }
}
