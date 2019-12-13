package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository.GuestDao;

public class GuestService implements IGuestService {
    private IGuestDao guestDao = new GuestDao();

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
}
