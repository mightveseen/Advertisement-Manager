package com.senlainc.git_courses.java_training.petushok_valiantsin.impl.repository.guest;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.guest.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.impl.model.guest.Guest;

public class GuestDao implements IGuestDao {
    private Guest guestArray[] = new Guest[256];
}
