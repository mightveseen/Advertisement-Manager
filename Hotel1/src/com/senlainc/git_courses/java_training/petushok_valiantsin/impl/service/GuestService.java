package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Room;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository.GuestDao;

import java.util.Comparator;

public class GuestService implements IGuestService {
    private IGuestDao guestDao = new GuestDao();

    private Comparator<Guest> SORT_BY_ALPHABET = new Comparator<Guest>() {
        @Override
        public int compare(Guest firstGuest, Guest lastGuest) {
            return firstGuest.getFirstName().compareTo(lastGuest.getFirstName());
        }
    };

    @Override
    public void add(Guest guest) {
        try {
            guestDao.create(guest);
        } catch (Exception e) {
            System.err.println("Wrong data.");
        }
    }

    @Override
    public void delete(int index) {
        try {
            guestDao.delete(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Guest with index: " + index + " dont exists.");
        }
    }

    @Override
    public void changeInfoContact(int index, String information) {
        Guest guest = new Guest(guestDao.read(index));
        guest.setInfoContact(information);
        guestDao.update(index, guest);
    }

    @Override
    public void numGuest() {
        System.out.println("\nNumber of guest: " + guestDao.readAll().size());
    }

    @Override
    public void showGuest() {
        for (int i = 0; i < guestDao.readAll().size(); i++) {
            System.out.print(guestDao.read(i));
        }
    }

    @Override
    public void sort(String parameter) {
        switch (parameter) {
            case "alphabet":
                sortByAlphabet();
                break;
        }
    }

    private void sortByAlphabet() {
        guestDao.readAll().sort(SORT_BY_ALPHABET);
    }
}
