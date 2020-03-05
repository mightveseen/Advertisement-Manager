package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.ConnectionManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration.GuestConfig;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotFoundException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.MaxElementsException;

import java.time.LocalDate;
import java.util.List;

@DependencyClass
@DependencyPrimary
public class GuestService implements IGuestService {
    private static final String ELEMENT_NOT_FOUND = "Guest with index: %d dont exists.";
    @DependencyComponent
    private static GuestConfig guestConfig;
    @DependencyComponent
    private IGuestDao guestDao;
    @DependencyComponent
    private ConnectionManager connectionManager;

    @Override
    public void add(String firstName, String lastName, LocalDate birthday) {
        int guestLimit = guestConfig.getGuestLimit();
        if (guestLimit < guestDao.readSize()) {
            throw new MaxElementsException(String.format("The number of guests exceeds the specified limit: %d guests", guestLimit));
        }
        guestDao.create(new Guest(firstName, lastName, birthday));
        connectionManager.commit();
    }

    @Override
    public void delete(int index) {
        try {
            guestDao.delete(index);
            connectionManager.commit();
        } catch (ElementNotFoundException e) {
            throw new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, index), e);
        }
    }

    @Override
    public int num() {
        return guestDao.readSize();
    }

    @Override
    public List<Guest> show() {
        return guestDao.readAll();
    }
}
