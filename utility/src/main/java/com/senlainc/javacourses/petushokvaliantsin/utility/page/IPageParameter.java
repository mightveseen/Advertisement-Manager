package com.senlainc.javacourses.petushokvaliantsin.utility.page;

import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.domain.Sort;

public interface IPageParameter {

    int getFirstElement();

    int getMaxResult();

    Sort.Direction getDirection();

    <E, F> SingularAttribute<E, F>[] getCriteriaField();
}
