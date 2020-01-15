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
        guestList.remove(guestList.stream().filter(i -> i.getId() == index).findFirst().orElseThrow(NullPointerException::new));
    }

    @Override
    public void update(Guest guest) {
        guestList.add(guestList.stream().filter(i -> i.getId() == guest.getId()).findFirst().orElseThrow(NullPointerException::new).getId(), guest);
    }

    @Override
    public List<Guest> readAll() {
        return guestList;
    }

    @Override
    public Guest read(int index) {
        return guestList.stream().filter(i -> i.getId() == index).findFirst().orElseThrow(NullPointerException::new);
    }
}
