package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.CustomEntityManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration.GuestConfig;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotFoundException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.MaxElementsException;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@DependencyClass
@DependencyPrimary
public class GuestService implements IGuestService {

    private static final String ELEMENT_NOT_FOUND = "Guest with index: %d don't exists.";
    @DependencyComponent
    private static GuestConfig guestConfig;
    @DependencyComponent
    private IGuestDao guestDao;
    private final EntityManager entityManager = CustomEntityManager.getEntityManager();

    @Override
    public void add(String firstName, String lastName, LocalDate birthday) {
        entityManager.getTransaction().begin();
        int guestLimit = guestConfig.getGuestLimit();
        if (guestLimit < guestDao.readSize()) {
            throw new MaxElementsException(String.format("The number of guests exceeds the specified limit: %d guests", guestLimit));
        }
        guestDao.create(new Guest(firstName, lastName, birthday));
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(int index) {
        try {
            entityManager.getTransaction().begin();
            guestDao.delete((long) index);
            entityManager.getTransaction().commit();
        } catch (ElementNotFoundException e) {
            throw new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, index), e);
        }
    }

    @Override
    public Long num() {
        return guestDao.readSize();
    }

    @Override
    public List<Guest> show() {
        return guestDao.readAll();
    }
}
