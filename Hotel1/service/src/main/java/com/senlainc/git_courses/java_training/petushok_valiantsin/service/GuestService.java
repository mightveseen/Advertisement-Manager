package com.senlainc.git_courses.java_training.petushok_valiantsin.service;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.api.service.IGuestService;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.CustomEntityManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.configuration.GuestConfig;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.data.MaxResult;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.CreateQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.DeleteQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.ReadQueryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class GuestService implements IGuestService {

    private static final Logger LOGGER = LogManager.getLogger(GuestService.class);
    private final EntityManager entityManager = CustomEntityManager.getEntityManager();
    private final IGuestDao guestDao;

    @Autowired
    public GuestService(IGuestDao guestDao) {
        this.guestDao = guestDao;
    }

    @Override
    public void add(String firstName, String lastName, LocalDate birthday) {
        final int guestLimit = GuestConfig.getGuestLimit();
        if (guestLimit < guestDao.readSize()) {
            LOGGER.info("The number of guest's exceeds the specified limit: {} guest's", guestLimit);
            return;
        }
        try {
            entityManager.getTransaction().begin();
            guestDao.create(new Guest(firstName, lastName, birthday));
            entityManager.getTransaction().commit();
            LOGGER.info("Add guest in database");
        } catch (CreateQueryException e) {
            entityManager.getTransaction().rollback();
            LOGGER.warn("Error while creating guest", e);
        }
    }

    @Override
    public void delete(int index) {
        try {
            entityManager.getTransaction().begin();
            guestDao.delete((long) index);
            entityManager.getTransaction().commit();
            LOGGER.info("Delete guest with index: {} from database", index);
        } catch (DeleteQueryException e) {
            entityManager.getTransaction().rollback();
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
