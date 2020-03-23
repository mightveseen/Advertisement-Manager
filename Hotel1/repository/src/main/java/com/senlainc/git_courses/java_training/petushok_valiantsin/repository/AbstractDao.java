package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.ICommonDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.CustomEntityManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.CreateQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.DeleteQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.ReadQueryException;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.dao.UpdateQueryException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractDao<T, K extends Serializable> implements ICommonDao<T, K> {

    protected static final String ERROR = "Error during connection to Database with entity: ";
    protected final EntityManager entityManager;
    protected final Class<T> clazz;

    public AbstractDao() {
        this.clazz = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        this.entityManager = CustomEntityManager.getEntityManager();
    }

    @Override
    public void create(T object) {
        try {
            entityManager.persist(object);
        } catch (PersistenceException e) {
            throw new CreateQueryException(ERROR + clazz.getSimpleName(), e);
        }
    }

    @Override
    public void delete(K index) {
        try {
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaDelete<T> criteriaDelete = criteriaBuilder.createCriteriaDelete(this.clazz);
            final Root<T> root = criteriaDelete.from(this.clazz);
            final Predicate predicate = criteriaBuilder.equal(root.get("id"), index);
            entityManager.createQuery(criteriaDelete
                    .where(predicate))
                    .executeUpdate();
        } catch (PersistenceException e) {
            throw new DeleteQueryException(ERROR + clazz.getSimpleName(), e);
        }
    }

    @Override
    public void update(T object) {
        try {
            entityManager.merge(object);
        } catch (PersistenceException e) {
            throw new UpdateQueryException(ERROR + clazz.getSimpleName(), e);
        }
    }

    @Override
    public T read(K index) {
        try {
            return entityManager.find(this.clazz, index);
        } catch (PersistenceException e) {
            throw new ReadQueryException(ERROR + clazz.getSimpleName(), e);
        }
    }

    @Override
    public List<T> readAll(int fistElement, int maxResult) {
        try {
            if (fistElement < 0) {
                fistElement = 0;
            }
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.clazz);
            return entityManager.createQuery(criteriaQuery
                    .select(criteriaQuery.from(this.clazz)))
                    .setFirstResult(fistElement)
                    .setMaxResults(maxResult)
                    .getResultList();
        } catch (PersistenceException e) {
            throw new ReadQueryException(ERROR + clazz.getSimpleName(), e);
        }
    }

    @Override
    public List<T> readAll(int fistElement, int maxResult, String sortParameter) {
        try {
            if (fistElement < 0) {
                fistElement = 0;
            }
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.clazz);
            final Root<T> root = criteriaQuery.from(this.clazz);
            return entityManager.createQuery(criteriaQuery
                    .select(root)
                    .orderBy(criteriaBuilder.asc(root.get(sortParameter))))
                    .setFirstResult(fistElement)
                    .setMaxResults(maxResult)
                    .getResultList();
        } catch (PersistenceException e) {
            throw new ReadQueryException(ERROR + clazz.getSimpleName(), e);
        }
    }

    @Override
    public Long readSize() {
        try {
            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            return entityManager.createQuery(criteriaQuery
                    .select(criteriaBuilder.count(criteriaQuery.from(this.clazz))))
                    .getSingleResult();
        } catch (PersistenceException e) {
            throw new ReadQueryException(ERROR + clazz.getSimpleName(), e);
        }
    }
}
