package com.senlainc.javacourses.petushokvaliantsin.utility.sort;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

public interface IPageParameter {

    boolean hasSort();

    int getFirstElement();

    int getMaxResult();

    <E> Order getOrder(CriteriaBuilder criteriaBuilder, Root<E> root);

    <E, F> SingularAttribute<E, F> getCriteriaField();
}
