package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository.AttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.utility.MyList;

import java.util.Comparator;

public class AttendanceService implements IAttendanceService {
    private final IAttendanceDao attendanceDao = new AttendanceDao();

    private final Comparator<Attendance> SORT_BY_SECTION = new Comparator<>() {
        @Override
        public int compare(Attendance firstAttendance, Attendance lastAttendance) {
            return firstAttendance.getSection().compareTo(lastAttendance.getSection());
        }
    };

    private final Comparator<Attendance> SORT_BY_PRICE = new Comparator<>() {
        @Override
        public int compare(Attendance firstAttendance, Attendance lastAttendance) {
            return String.valueOf(firstAttendance.getPrice()).compareTo(String.valueOf(lastAttendance.getPrice()));
        }
    };

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
        attendanceDao.update(index, attendance);
    }

    @Override
    public void sort(String parameter) {
        switch (parameter) {
            case "section":
                sortBySection();
                break;
            case "price":
                sortByPrice();
                break;
        }
    }

    private void sortBySection() {
        MyList<Attendance> myList = new MyList<>();
        for (int i = 0; i < attendanceDao.readAll().size(); i++) {
            myList.add(attendanceDao.read(i));
        }
        myList.sort(SORT_BY_SECTION);
        for (int i = 0; i < myList.size(); i++) {
            System.out.print(myList.get(i));
        }
    }

    private void sortByPrice() {
        MyList<Attendance> myList = new MyList<>();
        for (int i = 0; i < attendanceDao.readAll().size(); i++) {
            myList.add(attendanceDao.read(i));
        }
        myList.sort(SORT_BY_PRICE);
        for (int i = 0; i < myList.size(); i++) {
            System.out.print(myList.get(i));
        }
    }
}
