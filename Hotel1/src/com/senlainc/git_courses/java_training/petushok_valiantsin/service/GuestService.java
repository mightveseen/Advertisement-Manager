package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration.GuestConfig;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotFoundException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.MaxElementsException;

import java.time.LocalDate;
import java.util.List;

@DependencyClass
@DependencyPrimary
public class GuestService implements IGuestService {
    @DependencyComponent
    private static GuestConfig guestConfig;
    @DependencyComponent
    private IGuestDao guestDao;
    private static final String ELEMENT_NOT_FOUND = "Guest with index: %d dont exists.";

    @Override
    public void load() {
        guestDao.setAll();
    }

    @Override
    public void add(String firstName, String lastName, LocalDate birthday, String infoContact) {
        int guestLimit = guestConfig.getGuestLimit();
        if (guestLimit < guestDao.readAll().size()) {
            throw new MaxElementsException(String.format("The number of guests exceeds the specified limit: %d guests", guestLimit));
        }
        guestDao.create(new Guest(firstName, lastName, birthday, infoContact));
    }

    @Override
    public void delete(int index) {
        try {
            guestDao.delete(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, index), e);
        }
    }

    @Override
    public void changeInfoContact(int index, String information) {
        try {
            final Guest guest = guestDao.read(index);
            guest.setInfoContact(information);
            guestDao.update(guest);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND, index), e);
        }
    }

    @Override
    public int num() {
        return guestDao.readAll().size();
    }

    @Override
    public List<Guest> show() {
        return guestDao.readAll();
    }
}
