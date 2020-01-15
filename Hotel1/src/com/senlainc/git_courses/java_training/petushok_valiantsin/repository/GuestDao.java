package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestDao implements IGuestDao {
    private final List<Guest> guestList = new ArrayList<>();

    @Override
    public void create(Guest guest) {
        guestList.add(guest);
    }

    @Override
    public void delete(int index) {
        try {
            guestList.remove(guestList.stream().filter(i -> i.getId() == index).findFirst().orElse(null));
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    public void update(Guest guest) {
        try {
            guestList.add(guestList.stream().filter(i -> i.getId() == guest.getId()).findFirst().orElse(null).getId(), guest);
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    public List<Guest> readAll() {
        return guestList;
    }

    @Override
    public Guest read(int index) {
        return guestList.stream().filter(i -> i.getId() == index).findFirst().orElse(null);
    }
}
