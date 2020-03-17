package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.ICommonDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.DaoException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T, K extends Serializable> implements ICommonDao<T, K> {

    private static final String ERROR = "Error during connection to Database. Check query.";
    @PersistenceContext(unitName = "persistence", type = PersistenceContextType.EXTENDED)
    protected EntityManager entityManager;
    private Class<T> clazz;

    public AbstractDao() {

    }

    protected void setClass(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void create(T object) {

    }

    @Override
    public void delete(K object) {

    }

    @Override
    public void update(T object) {

    }

    @Override
    public T read(K index) {
        return null;
    }

    @Override
    public List<T> readAll() {
        entityManager.getTransaction().begin();
        List<T> ff = new ArrayList<>();
        try {
            ff.add(entityManager.find(clazz, (long) 1));
        } catch (Exception e) {
            throw new DaoException(ERROR, e);
        }
        entityManager.getTransaction().commit();
        return ff;
    }
}
