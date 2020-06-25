package com.senlainc.javacourses.petushokvaliantsin.utility.page;

import org.springframework.data.domain.Sort;

import javax.persistence.metamodel.SingularAttribute;

public interface IPageParameter {

    int getFirstElement();

    void setFirstElement(int firstElement);

    int getMaxResult();

    void setMaxResult(int maxResult);

    Sort.Direction getDirection();

    <E, F> SingularAttribute<E, F>[] getCriteriaField();
}
