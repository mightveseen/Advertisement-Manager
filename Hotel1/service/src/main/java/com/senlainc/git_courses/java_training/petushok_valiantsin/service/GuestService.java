package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.data.MaxResult;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.ElementNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@PropertySource(value = {"classpath:/properties/guest.properties"}, ignoreResourceNotFound = true)
public class GuestService implements IGuestService {

    private final IGuestDao guestDao;
    @Value(value = "${GUEST_CONFIG.GUEST_LIMIT_VALUE:100}")
    private int guestLimitProperty;

    @Autowired
    public GuestService(IGuestDao guestDao) {
        this.guestDao = guestDao;
    }

    @Override
    @Transactional
    public void add(String firstName, String lastName, LocalDate birthday) {
        if (guestLimitProperty > guestDao.readSize()) {
            guestDao.create(new Guest(firstName, lastName, birthday));
        } else {
            throw new ElementNotAvailableException("The number of guest's exceeds the specified limit: " + guestLimitProperty + " guest's");
        }
    }

    @Override
    @Transactional
    public void delete(long index) {
        guestDao.delete(guestDao.read(index));
    }

    @Override
    public Long num() {
        return guestDao.readSize();
    }

    @Override
    public List<Guest> getGuestList() {
        final int maxResult = MaxResult.GUEST.getMaxResult();
        return guestDao.readAll(guestDao.readSize().intValue() - maxResult, maxResult);
    }
}
