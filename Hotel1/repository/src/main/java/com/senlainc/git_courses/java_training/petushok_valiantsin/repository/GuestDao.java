package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QueryDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.enumeration.QueryType;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.DaoException;

public class GuestDao extends AbstractDao<Guest, Long> implements IGuestDao {

    private static final String ERROR = "Error during connection to Database. Check query.";

    @Override
    public Long readSize() {
        try {
            return (Long) entityManager.createQuery(QueryDao.GUEST.getQuery(QueryType.SIZE)).getSingleResult();
        } catch (Exception e) {
            throw new DaoException(ERROR, e);
        }
    }
}
