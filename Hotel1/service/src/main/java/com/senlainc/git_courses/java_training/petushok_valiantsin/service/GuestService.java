package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.data.MaxResult;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.CreateQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.DeleteQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.ReadQueryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@PropertySource(value = {"classpath:/properties/guest.properties"}, ignoreResourceNotFound = true)
public class GuestService implements IGuestService {

    private static final Logger LOGGER = LogManager.getLogger(GuestService.class);
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
            try {
                guestDao.create(new Guest(firstName, lastName, birthday));
                LOGGER.info("Add guest in database");
            } catch (CreateQueryException e) {
                LOGGER.warn("Error while creating guest", e);
            }
        } else {
            LOGGER.info("The number of guest's exceeds the specified limit: {} guest's", guestLimitProperty);
        }
    }

    @Override
    @Transactional
    public void delete(int index) {
        try {
            guestDao.delete((long) index);
            LOGGER.info("Delete guest with index: {} from database", index);
        } catch (DeleteQueryException e) {
            LOGGER.warn("Error while deleting guest.", e);
        }
    }

    @Override
    public Long num() {
        try {
            final Long num = guestDao.readSize();
            LOGGER.info("Show number of guest");
            return num;
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read guest's. Read operation: number of guest's", e);
        }
        return null;
    }

    @Override
    public List<Guest> getGuestList() {
        final int maxResult = MaxResult.GUEST.getMaxResult();
        try {
            return guestDao.readAll(guestDao.readSize().intValue() - maxResult, maxResult);
        } catch (ReadQueryException e) {
            LOGGER.warn("Error while read all guest's.", e);
        }
        return Collections.emptyList();
    }
}
