package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IAttendanceService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.CustomEntityManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotFoundException;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;

@DependencyClass
@DependencyPrimary
public class AttendanceService implements IAttendanceService {

    private static final String ELEMENT_NOT_FOUND = "Attendance with index: %d don't exists.";
    private final EntityManager entityManager = CustomEntityManager.getEntityManager();
    @DependencyComponent
    private IAttendanceDao attendanceDao;

    @Override
    public void add(String name, String section, double price) {
        entityManager.getTransaction().begin();
        attendanceDao.create(new Attendance(name, section, price));
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(int index) {
        attendanceDao.delete((long) index);
    }

    @Override
    public void changePrice(int index, double price) {
        try {
            final Attendance attendance = attendanceDao.read((long) index);
            attendance.setPrice(price);
            attendanceDao.update(attendance);
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
        myList.sort(Comparator.comparing(Attendance::getSection));
    }

    private void sortByPrice(List<Attendance> myList) {
        myList.sort(Comparator.comparing(Attendance::getPrice));
    }
}
