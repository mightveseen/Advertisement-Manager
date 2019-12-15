package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.utility.MyList;

public class GuestDao implements IGuestDao {
    private MyList<Guest> guestMyList = new MyList<>();

    @Override
    public void create(Guest guest) {
        guestMyList.add(guest);
    }

    @Override
    public void delete(int index) {
        guestMyList.remove(index);
    }

    @Override
    public void update(int index, Guest guest) {
        guestMyList.add(index, guest);
    }

    @Override
    public MyList<Guest> readAll() {
        return guestMyList;
    }

    @Override
    public Guest read(int index) {
        return guestMyList.get(index);
    }
}
