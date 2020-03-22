package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IGuestDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Guest;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.ReadQueryException;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class GuestDao extends AbstractDao<Guest, Long> implements IGuestDao {

    @Override
    public Long readSize() {
        try {
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            return entityManager.createQuery(criteriaQuery
                    .select(criteriaBuilder.count(criteriaQuery.from(Guest.class))))
                    .getSingleResult();
        } catch (PersistenceException e) {
            throw new ReadQueryException(ERROR + clazz.getSimpleName(), e);
        }
    }
}
