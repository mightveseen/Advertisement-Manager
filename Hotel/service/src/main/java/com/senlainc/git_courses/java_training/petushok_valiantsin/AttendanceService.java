package com.senlainc.git_courses.java_training.petushok_valiantsin;

import com.senlainc.git_courses.java_training.petushok_valiantsin.base_conection.ConnectionManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.exception.ElementNotFoundException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.sort.Sort;
import com.senlainc.git_courses.java_training.petushok_valiantsin.sort.SortParameter;

import java.util.List;

@DependencyClass
@DependencyPrimary
public class AttendanceService implements IAttendanceService {
    private static final String ELEMENT_NOT_FOUND = "Attendance with index: %d dont exists.";
    @DependencyComponent
    private IAttendanceDao attendanceDao;
    @DependencyComponent
    private ConnectionManager connectionManager;

    @Override
    public void add(String name, String section, double price) {
        attendanceDao.create(new Attendance(name, section, price));
        connectionManager.commit();
    }

    @Override
    public void delete(int index) {
        attendanceDao.delete(index);
        connectionManager.commit();
    }

    @Override
    public void changePrice(int index, double price) {
        try {
            final Attendance attendance = attendanceDao.read(index);
            attendance.setPrice(price);
            attendanceDao.update(attendance);
            connectionManager.commit();
        } catch (ElementNotFoundException e) {
            throw new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, index), e);
        }
    }

    @Override
    public List<Attendance> getAttendanceList() {
        return attendanceDao.readAll();
    }

    @Override
    public List<Attendance> sort(String parameter) {
        final List<Attendance> myList = attendanceDao.readAll();
        switch (parameter) {
            case "section":
                sortBySection(myList);
                break;
            case "price":
                sortByPrice(myList);
                break;
            default:
                break;
        }
        return myList;
    }

    private void sortBySection(List<Attendance> myList) {
        myList.sort(Sort.ATTENDANCE.getComparator(SortParameter.SECTION));
    }

    private void sortByPrice(List<Attendance> myList) {
        myList.sort(Sort.ATTENDANCE.getComparator(SortParameter.PRICE));
    }
}
