package com.senlainc.javacourses.petushokvaliantsin.utility.sort;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

public interface IPageParameter {

    boolean hasSort();

    int getFirstElement();

    int getMaxResult();

    <E> Order getSort(CriteriaBuilder criteriaBuilder, Root<E> root);
}
