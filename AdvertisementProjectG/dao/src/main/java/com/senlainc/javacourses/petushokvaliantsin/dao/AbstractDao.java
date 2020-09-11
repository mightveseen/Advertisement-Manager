package com.senlainc.javacourses.petushokvaliantsin.dao;

import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Objects;

public abstract class AbstractDao<E> {

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
}
