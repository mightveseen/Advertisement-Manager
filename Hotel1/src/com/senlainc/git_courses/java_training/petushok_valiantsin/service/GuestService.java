package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;

import java.time.LocalDate;
import java.util.ArrayList;
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
        guestDao.create(new Guest(firstName, lastName, birthday, infoContact));
    }

    @Override
    public void delete(int index) {
        try {
            guestDao.delete(index);
        } catch (NullPointerException e) {
            System.err.println("Guest with index: " + index + " dont exists.");
        }
    }

    @Override
    public void changeInfoContact(int index, String information) {
        try {
            Guest guest = guestDao.read(index);
            guest.setInfoContact(information);
            guestDao.update(guest);
        } catch (NullPointerException e) {
            System.err.println("Guest with index: " + index + " dont exists.");
        }
    }

    @Override
    public void num() {
        System.out.println("Number of guest: " + guestDao.readAll().size());
    }

    @Override
    public void show() {
        for(Guest guest : guestDao.readAll()) {
            System.out.println(guest);
        }
    }

    @Override
    public Guest getGuest(int index) {
        if(index > guestDao.readAll().size()) {
            throw new NullPointerException("Guest with index: " + index + " dont exists.");
        }
        return guestDao.read(index);
    }

    @Override
    public int[] sortByAlphabet() {
        List<Guest> myList = new ArrayList<>(guestDao.readAll());
        myList.sort(SORT_BY_ALPHABET);
        return getGuestIndex(myList);
    }

    private int[] getGuestIndex(List<Guest> myList) {
        int[] guestIndex = new int[myList.size()];
        for (int i = 0; i < myList.size(); i++) {
            guestIndex[i] = myList.get(i).getId();
        }
        return guestIndex;
    }
}
