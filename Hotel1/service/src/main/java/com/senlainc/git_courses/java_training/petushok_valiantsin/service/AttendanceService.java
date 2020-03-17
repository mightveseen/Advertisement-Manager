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
    public void delete(long index) {
        entityManager.getTransaction().begin();
        attendanceDao.delete(index);
        entityManager.getTransaction().commit();
    }

    @Override
    public void changePrice(long index, double price) {
        try {
            entityManager.getTransaction().begin();
            final Attendance attendance = attendanceDao.read(index);
            attendance.setPrice(price);
            attendanceDao.update(attendance);
            entityManager.getTransaction().commit();
        } catch (ElementNotFoundException e) {
            entityManager.getTransaction().rollback();
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
