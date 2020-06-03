package com.senlainc.javacourses.petushokvaliantsin.repository;

import com.senlainc.javacourses.petushokvaliantsin.model.State;
import com.senlainc.javacourses.petushokvaliantsin.model.State_;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.IStateDao;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.ReadQueryException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class StateDao implements IStateDao {

    private EntityManager entityManager;

    @PersistenceContext(name = "mainPersistence", type = PersistenceContextType.EXTENDED)
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public State read(String description) {
        try {
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<State> criteriaQuery = criteriaBuilder.createQuery(State.class);
            final Root<State> root = criteriaQuery.from(State.class);
            final Predicate predicate = criteriaBuilder.equal(root.get(State_.description), description);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .where(predicate))
                    .getSingleResult();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }
}
