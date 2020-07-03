package com.senlainc.javacourses.petushokvaliantsin.utility.page;

import org.springframework.data.domain.Sort;

import javax.persistence.metamodel.SingularAttribute;

public interface IPageParameter {

    int getFirstElement();

    int getMaxResult();

    Sort.Direction getDirection();

    <E, F> SingularAttribute<E, F>[] getCriteriaField();
}
