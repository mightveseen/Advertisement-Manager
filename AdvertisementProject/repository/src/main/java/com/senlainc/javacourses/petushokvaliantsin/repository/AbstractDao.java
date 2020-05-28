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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractDao<E, K extends Serializable> implements IGenericDao<E, K> {

    protected final Class<E> entityClazz;
    protected final Class<K> keyClazz;
    protected EntityManager entityManager;
    protected CriteriaBuilder criteriaBuilder;

    public AbstractDao() {
        this.entityClazz = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.keyClazz = (Class<K>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @PersistenceContext(name = "mainPersistence", type = PersistenceContextType.EXTENDED)
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
            return entityManager.find(entityClazz, index);
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    //TODO : Fix index
    @Override
    public <F> F read(K index, SingularAttribute<E, F> field) {
        try {
            final CriteriaQuery<F> criteriaQuery = criteriaBuilder.createQuery(field.getJavaType());
            final Root<F> root = criteriaQuery.from(field.getJavaType());
            final Predicate predicate = criteriaBuilder.equal(root.get("index"), index);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .where(predicate))
                    .getSingleResult();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public List<E> readAll(int firstElement, int maxResult) {
        try {
            final CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClazz);
            final Root<E> root = criteriaQuery.from(entityClazz);
            return entityManager.createQuery(criteriaQuery
                    .select(root))
                    .setFirstResult(firstElement)
                    .setMaxResults(maxResult)
                    .getResultList();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public <F> List<E> readAll(int firstElement, int maxResult, SingularAttribute<E, F> sortField) {
        try {
            final CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClazz);
            final Root<E> root = criteriaQuery.from(entityClazz);
            final Order order = criteriaBuilder.asc(root.get(sortField));
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .orderBy(order))
                    .setFirstResult(firstElement)
                    .setMaxResults(maxResult)
                    .getResultList();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public <F> List<E> readAll(int firstElement, int maxResult, SingularAttribute<E, F> field, F value) {
        try {
            final CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClazz);
            final Root<E> root = criteriaQuery.from(entityClazz);
            final Predicate predicate = criteriaBuilder.equal(root.get(field), value);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .where(predicate))
                    .setFirstResult(firstElement)
                    .setMaxResults(maxResult)
                    .getResultList();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public <F, C> List<E> readAll(int firstElement, int maxResult, SingularAttribute<E, F> firstSortField,
                                  SingularAttribute<F, C> secondSortField) {
        try {
            final CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClazz);
            final Root<E> root = criteriaQuery.from(entityClazz);
            final Order order = criteriaBuilder.asc(root.get(firstSortField).get(secondSortField));
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .orderBy(order))
                    .setFirstResult(firstElement)
                    .setMaxResults(maxResult)
                    .getResultList();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public K readSize() {
        try {
            final CriteriaQuery<K> criteriaQuery = criteriaBuilder.createQuery(keyClazz);
            final Root<E> root = criteriaQuery.from(entityClazz);
            return entityManager.createQuery(criteriaQuery
                    .select((Selection<? extends K>) criteriaBuilder.count(root)))
                    .getSingleResult();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }
}
