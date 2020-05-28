package com.senlainc.javacourses.petushokvaliantsin.repository;

import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.IGenericDao;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.CreateQueryException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.DeleteQueryException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.ReadQueryException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.UpdateQueryException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractDao<E, K extends Serializable> implements IGenericDao<E, K> {

    protected final Class<E> clazz;
    protected EntityManager entityManager;

    public AbstractDao() {
        this.clazz = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @PersistenceContext(name = "mainPersistence", type = PersistenceContextType.EXTENDED)
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(E object) {
        try {
            entityManager.persist(object);
        } catch (PersistenceException exc) {
            throw new CreateQueryException(exc);
        }
    }

    @Override
    public void delete(E object) {
        try {
            entityManager.remove(object);
        } catch (PersistenceException exc) {
            throw new DeleteQueryException(exc);
        }
    }

    @Override
    public void update(E object) {
        try {
            entityManager.merge(object);
        } catch (PersistenceException exc) {
            throw new UpdateQueryException(exc);
        }
    }

    @Override
    public E read(K index) {
        try {
            return entityManager.find(clazz, index);
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public <F> E read(SingularAttribute<E, F> field) {
        try {
//            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//            final CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(clazz);
//            return entityManager.createQuery(criteriaQuery
//                    .select(criteriaQuery.from(clazz))).getSingleResult();
            return null;
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public <F> F read(K index, SingularAttribute<E, F> field) {
        return null;
    }

    @Override
    public List<E> readAll(int firstElement, int maxResult) {
        return null;
    }

    @Override
    public <F> List<E> readAll(int firstElement, int maxResult, SingularAttribute<E, F> sortField) {
        return null;
    }

    @Override
    public <F> List<E> readAll(int firstElement, int maxResult, SingularAttribute<E, F> sortField, F parameter) {
        return null;
    }

    @Override
    public K readSize() {
        return null;
    }
}
