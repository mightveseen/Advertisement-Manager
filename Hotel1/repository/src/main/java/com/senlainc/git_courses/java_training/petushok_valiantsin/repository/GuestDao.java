package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.DaoException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class GuestDao extends AbstractDao<Guest, Long> implements IGuestDao {

    private static final String ERROR = "Error during connection to Database. Check query.";

    @Override
    public Long readSize() {
        try {
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(Guest.class)));
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception e) {
            throw new DaoException(ERROR, e);
        }
    }
}
