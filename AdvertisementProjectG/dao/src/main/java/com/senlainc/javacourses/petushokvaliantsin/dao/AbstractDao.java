package com.senlainc.javacourses.petushokvaliantsin.dao;

import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class AbstractDao<E> {

    protected EntityManager entityManager;
    protected CriteriaBuilder criteriaBuilder;

    @PersistenceContext(name = "mainPersistence")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    protected Order getOrder(IPageParameter pageParameter, CriteriaBuilder criteriaBuilder, Root<E> root) {
        final Function<Expression<?>, Order> function = expression -> pageParameter.getDirection().isDescending() ?
                criteriaBuilder.desc(expression) :
                criteriaBuilder.asc(expression);
        final BiFunction<Integer, Function<Expression<?>, Order>, Order> lengthFunction = (length, func) -> length > 1 ?
                func.apply(root.get(pageParameter.getCriteriaField()[0]).get(pageParameter.getCriteriaField()[1])) :
                func.apply(root.get(pageParameter.getCriteriaField()[0]));
        if (pageParameter.getCriteriaField() == null || Arrays.stream(pageParameter.getCriteriaField()).anyMatch(Objects::isNull)) {
            return function.apply(root);
        }
        return lengthFunction.apply(pageParameter.getCriteriaField().length, function);
    }
}
