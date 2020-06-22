package com.senlainc.javacourses.petushokvaliantsin.repository;

import com.senlainc.javacourses.petushokvaliantsin.repositoryapi.IGenericDao;
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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

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
            return entityManager.find(entityClazz, index);
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public List<E> readAll() {
        try {
            final CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClazz);
            final Root<E> root = criteriaQuery.from(entityClazz);
            return entityManager.createQuery(criteriaQuery
                    .select(root))
                    .getResultList();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public List<E> readAll(IPageParameter pageParameter) {
        try {
            final CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClazz);
            final Root<E> root = criteriaQuery.from(entityClazz);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .orderBy(getOrder(pageParameter, criteriaBuilder, root)))
                    .setFirstResult(pageParameter.getFirstElement())
                    .setMaxResults(pageParameter.getMaxResult())
                    .getResultList();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    @Override
    public <F> List<E> readAll(IPageParameter pageParameter, SingularAttribute<E, F> field, F value) {
        try {
            final CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClazz);
            final Root<E> root = criteriaQuery.from(entityClazz);
            final Predicate predicate = criteriaBuilder.equal(root.get(field), value);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .orderBy(getOrder(pageParameter, criteriaBuilder, root))
                    .where(predicate))
                    .setFirstResult(pageParameter.getFirstElement())
                    .setMaxResults(pageParameter.getMaxResult())
                    .getResultList();
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

    @Override
    public <F> Long readCount(SingularAttribute<E, F> field, F value) {
        try {
            final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            final Root<E> root = criteriaQuery.from(entityClazz);
            final Predicate predicate = criteriaBuilder.equal(root.get(field), value);
            return entityManager.createQuery(criteriaQuery
                    .select(criteriaBuilder.count(root))
                    .where(predicate))
                    .getSingleResult();
        } catch (PersistenceException exc) {
            throw new ReadQueryException(exc);
        }
    }

    protected Order getOrder(IPageParameter pageParameter, CriteriaBuilder criteriaBuilder, Root<E> root) {
        switch (pageParameter.getDirection()) {
            case ASC:
                if (pageParameter.getCriteriaField() == null) {
                    return criteriaBuilder.asc(root);
                }
                return pageParameter.getCriteriaField().length > 1 ?
                        criteriaBuilder.asc(root.join(pageParameter.getCriteriaField()[0]).get(pageParameter.getCriteriaField()[1])) :
                        criteriaBuilder.asc(root.get(pageParameter.getCriteriaField()[0]));
            case DESC:
                if (pageParameter.getCriteriaField() == null) {
                    return criteriaBuilder.desc(root);
                }
                return pageParameter.getCriteriaField().length > 1 ?
                        criteriaBuilder.desc(root.join(pageParameter.getCriteriaField()[0]).get(pageParameter.getCriteriaField()[1])) :
                        criteriaBuilder.desc(root.get(pageParameter.getCriteriaField()[0]));
            default:
                return criteriaBuilder.asc(root);
        }
    }
}
