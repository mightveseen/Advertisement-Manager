package com.senlainc.javacourses.petushokvaliantsin.repository;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.IGenericDao;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.CreateQueryException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.DeleteQueryException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.ReadQueryException;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.dao.UpdateQueryException;
import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public abstract class AbstractDao<E, K extends Serializable> implements IGenericDao<E, K> {

    protected final Class<E> entityClazz;
    protected EntityManager entityManager;
    protected CriteriaBuilder criteriaBuilder;

    public AbstractDao() {
        this.entityClazz = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @PersistenceContext(name = "mainPersistence")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
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
            final E object = entityManager.find(entityClazz, index);
            checkObjectOnNull(index, object);
            return object;
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public E read(K index, String graphName) {
        try {
            final E object = entityManager.find(entityClazz, index, Collections.singletonMap(GraphProperty.Type.FETCH, entityManager.getEntityGraph(graphName)));
            checkObjectOnNull(index, object);
            return object;
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public Long readCount() {
        try {
            final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            final Root<E> root = criteriaQuery.from(entityClazz);
            return entityManager.createQuery(criteriaQuery
                    .select(criteriaBuilder.count(root)))
                    .getSingleResult();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    protected Order getOrder(IPageParameter pageParameter, CriteriaBuilder criteriaBuilder, Root<E> root) {
        if (pageParameter.getCriteriaField() == null || Arrays.stream(pageParameter.getCriteriaField()).anyMatch(Objects::isNull)) {
            return pageParameter.getDirection().isDescending() ? criteriaBuilder.desc(root) : criteriaBuilder.asc(root);
        }
        switch (pageParameter.getDirection()) {
            case ASC:
                return pageParameter.getCriteriaField().length > 1 ?
                        criteriaBuilder.asc(root.get(pageParameter.getCriteriaField()[0]).get(pageParameter.getCriteriaField()[1])) :
                        criteriaBuilder.asc(root.get(pageParameter.getCriteriaField()[0]));
            case DESC:
                return pageParameter.getCriteriaField().length > 1 ?
                        criteriaBuilder.desc(root.get(pageParameter.getCriteriaField()[0]).get(pageParameter.getCriteriaField()[1])) :
                        criteriaBuilder.desc(root.get(pageParameter.getCriteriaField()[0]));
            default:
                return criteriaBuilder.asc(root);
        }
    }

    private void checkObjectOnNull(K index, E object) {
        if (object == null) {
            throw new EntityNotExistException("Entity [" + entityClazz.getSimpleName() + "] with index [" + index + "] not exist");
        }
    }
}
