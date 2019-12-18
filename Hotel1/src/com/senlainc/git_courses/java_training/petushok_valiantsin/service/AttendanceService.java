package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.MyList;

import java.util.Comparator;

public class AttendanceService implements IAttendanceService {
    private final IAttendanceDao attendanceDao;
    private final Comparator<Attendance> SORT_BY_SECTION = Comparator.comparing(Attendance::getSection);
    private final Comparator<Attendance> SORT_BY_PRICE = Comparator.comparing(firstAttendance -> String.valueOf(firstAttendance.getPrice()));

    public AttendanceService(IAttendanceDao attendanceDao) {
        this.attendanceDao = attendanceDao;
    }

    @Override
    public void add(Attendance attendance) {
        attendanceDao.create(attendance);
    }

    @Override
    public void delete(int index) {
        if (attendanceDao.readAll().size() < index) {
            System.err.println("Attendance with index: " + index + " dont exists.");
            return;
        }
        attendanceDao.delete(index);
    }

    @Override
    public double getPrice(int index) {
        return attendanceDao.read(index).getPrice();
    }

    @Override
    public Attendance get(int index) {
        return attendanceDao.read(index);
    }

    @Override
    public void changePrice(int index, double price) {
        Attendance attendance = new Attendance(attendanceDao.read(index));
        attendance.setPrice(price);
        attendanceDao.update(attendance);
    }

    @Override
    public MyList<Attendance> sort(String parameter) {
        MyList<Attendance> myList = new MyList<>();
        createBufList(myList);
        switch (parameter) {
            case "section":
                sortBySection(myList);
                return myList;
            case "price":
                sortByPrice(myList);
                return myList;
        }
        return null;
    }

    private void createBufList(MyList<Attendance> myList) {
        for (int i = 1; i <= attendanceDao.readAll().size(); i++) {
            myList.add(attendanceDao.read(i));
        }
    }

    private void sortBySection(MyList<Attendance> myList) {
        myList.sort(SORT_BY_SECTION);
    }

    private void sortByPrice(MyList<Attendance> myList) {
        myList.sort(SORT_BY_PRICE);
    }
}
