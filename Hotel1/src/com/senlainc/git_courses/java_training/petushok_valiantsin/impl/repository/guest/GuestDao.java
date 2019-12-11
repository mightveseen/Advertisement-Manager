package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository.guest;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.guest.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.guest.Guest;

public class GuestDao implements IGuestDao {
    private Guest guestArray[] = new Guest[256];

    @Override
    public void create(Guest guest) {

    }
    @Override
    public void delete(int index) {

    }
    @Override
    public void update(int index, Guest guest) {

    }
    @Override
    public Guest[] readAll() {
        return guestArray;
    }
    @Override
    public Guest readById(int index) {
        return guestArray[index];
    }
}
