package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
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
        guestDao.create(guest);
    }

    @Override
    public void delete(int index) {
        if (guestDao.readAll().size() < index) {
            System.err.println("Guest with index: " + index + " dont exists.");
            return;
        }
        guestDao.delete(index);
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
    public int[] sortByAlphabet() {
        MyList<Guest> myList = new MyList<>();
        createBufList(myList);
        myList.sort(SORT_BY_ALPHABET);
        return getGuestIndex(myList);
    }

    private int[] getGuestIndex(MyList<Guest> myList) {
        int[] guestIndex = new int[myList.size()];
        for (int i = 0; i < myList.size(); i++) {
            guestIndex[i] = myList.get(i).getId();
        }
        return guestIndex;
    }

    private void createBufList(MyList<Guest> myList) {
        for (int i = 1; i <= guestDao.readAll().size(); i++) {
            myList.add(guestDao.read(i));
        }
    }
}
