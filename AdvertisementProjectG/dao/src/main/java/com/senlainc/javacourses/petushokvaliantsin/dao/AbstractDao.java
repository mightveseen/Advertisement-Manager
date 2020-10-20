package com.senlainc.javacourses.petushokvaliantsin.dao;

import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
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
        final SingularAttribute<Object, Object>[] criteriaField = pageParameter.getCriteriaField();
        final Function<Expression<?>, Order> sortFunction = expression -> pageParameter.getDirection().isDescending() ?
                criteriaBuilder.desc(expression) : criteriaBuilder.asc(expression);
        final BiFunction<Integer, Function<Expression<?>, Order>, Order> lengthFunction = (length, func) -> length > 1 ?
                func.apply(root.get(criteriaField[0]).get(criteriaField[1])) :
                func.apply(root.get(criteriaField[0]));
        if (criteriaField == null || Arrays.stream(criteriaField).anyMatch(Objects::isNull)) {
            return sortFunction.apply(root);
        }
        return lengthFunction.apply(criteriaField.length, sortFunction);
    }
}
