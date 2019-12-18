package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.repository.GuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.MyList;

import java.util.Comparator;

public class GuestService implements IGuestService {
    private final IGuestDao guestDao;
    private final Comparator<Guest> SORT_BY_ALPHABET = Comparator.comparing(Guest::getFirstName);

    public GuestService(IGuestDao guestDao) {
        this.guestDao = guestDao;
    }

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
        guestDao.update(guest);
    }

    @Override
    public void num() {
        System.out.println("\nNumber of guest: " + guestDao.readAll().size());
    }

    @Override
    public void show() {
        for (int i = 0; i < guestDao.readAll().size(); i++) {
            System.out.print(guestDao.read(i));
        }
    }

    @Override
    public Guest getGuest(int index) {
        return guestDao.read(index);
    }

    @Override
    public void sortByAlphabet() {
        MyList<Guest> myList = new MyList<>();
        for (int i = 0; i < guestDao.readAll().size(); i++) {
            myList.add(guestDao.read(i));
        }
        myList.sort(SORT_BY_ALPHABET);
        for (int i = 0; i < myList.size(); i++) {
            System.out.print(myList.get(i));
        }
    }
}
