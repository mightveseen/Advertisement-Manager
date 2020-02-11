package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration.GuestConfig;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.sort.Sort;

import java.time.LocalDate;
import java.util.List;

@DependencyClass
@DependencyPrimary
public class GuestService implements IGuestService {
    @DependencyComponent
    private IGuestDao guestDao;

    @Override
    public void load() {
        guestDao.setAll();
    }

    @Override
    public void add(String firstName, String lastName, LocalDate birthday, String infoContact) {
        int guestLimit = GuestConfig.getInstance().getGuestLimit();
        if (guestLimit < guestDao.readAll().size()) {
            throw new RuntimeException("The number of guests exceeds the specified limit: " + guestLimit + " guests");
        }
        guestDao.create(new Guest(firstName, lastName, birthday, infoContact));
    }

    @Override
    public void delete(int index) {
        try {
            guestDao.delete(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Guest with index: " + index + " dont exists.", e);
        }
    }

    @Override
    public void changeInfoContact(int index, String information) {
        try {
            final Guest guest = guestDao.read(index);
            guest.setInfoContact(information);
            guestDao.update(guest);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Guest with index: " + index + " dont exists.", e);
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

    @Override
    public Guest getGuest(int index) {
        try {
            return guestDao.read(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Guest with index: " + index + " dont exists.", e);
        }
    }

    @Override
    public int[] sortByAlphabet() {
        final List<Guest> myList = guestDao.readAll();
        myList.sort(Sort.GUEST.getComparator("ALPHABET"));
        return getGuestIndex(myList);
    }

    private int[] getGuestIndex(List<Guest> myList) {
        final int[] guestIndex = new int[myList.size()];
        for (int i = 0; i < myList.size(); i++) {
            guestIndex[i] = myList.get(i).getId();
        }
        return guestIndex;
    }
}
