package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.MyList;

public class GuestDao implements IGuestDao {
    private final MyList<Guest> guestMyList = new MyList<>();

    @Override
    public void create(Guest guest) {
        guestMyList.add(guest);
    }

    @Override
    public void delete(int index) {
        for (int i = 0; i < guestMyList.size(); i++) {
            if (guestMyList.get(i).getId() == index) {
                guestMyList.remove(i);
                return;
            }
        }
    }

    @Override
    public void update(Guest guest) {
        for (int i = 0; i < guestMyList.size(); i++) {
            if (guestMyList.get(i).getId() == guest.getId()) {
                guestMyList.add(i, guest);
                return;
            }
        }
    }

    @Override
    public MyList<Guest> readAll() {
        return guestMyList;
    }

    @Override
    public Guest read(int index) {
        for (int i = 0; i < guestMyList.size(); i++) {
            if (guestMyList.get(i).getId() == index) {
                return guestMyList.get(i);
            }
        }
        return null;
    }
}
