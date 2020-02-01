package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration.GuestConfig;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class GuestService implements IGuestService {
    private final IGuestDao guestDao;
    private final Comparator<Guest> SORT_BY_ALPHABET = Comparator.comparing(Guest::getFirstName);

    public GuestService(IGuestDao guestDao) {
        this.guestDao = guestDao;
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
        myList.sort(SORT_BY_ALPHABET);
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
