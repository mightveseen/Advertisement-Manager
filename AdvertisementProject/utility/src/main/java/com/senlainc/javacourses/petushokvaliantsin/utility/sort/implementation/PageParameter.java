package com.senlainc.javacourses.petushokvaliantsin.utility.sort.implementation;

import com.senlainc.javacourses.petushokvaliantsin.utility.sort.IPageParameter;
import org.springframework.data.domain.Sort;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

public class PageParameter implements IPageParameter {

    private final int firstElement;
    private final int maxResult;
    private Sort.Direction sort;
    private SingularAttribute sortField;

    private PageParameter(int firstElement, int maxResult) {
        this.firstElement = firstElement;
        this.maxResult = maxResult;
    }

    private PageParameter(int fistElement, int maxResult, String direction) {
        this.firstElement = fistElement;
        this.maxResult = maxResult;
        this.sort = getDirection(direction);
    }

    private <E, F> PageParameter(int firstElement, int maxResult, String direction, SingularAttribute<E, F> sortField) {
        this.firstElement = firstElement;
        this.maxResult = maxResult;
        this.sort = getDirection(direction);
        this.sortField = sortField;
    }

    public static PageParameter of(int firstElement, int maxResult) {
        return new PageParameter(firstElement, maxResult);
    }

    public static PageParameter of(int firstElement, int maxResult, String direction) {
        return new PageParameter(firstElement, maxResult, direction);
    }

    public static <E, F> PageParameter of(int firstElement, int maxResult, String direction, SingularAttribute<E, F> sortField) {
        return new PageParameter(firstElement, maxResult, direction, sortField);
    }

    @Override
    public boolean hasSort() {
        return sort != null;
    }

    @Override
    public int getFirstElement() {
        return firstElement;
    }

    @Override
    public int getMaxResult() {
        return maxResult;
    }

    @Override
    public <E> Order getSort(CriteriaBuilder criteriaBuilder, Root<E> root) {
        if (sortField != null) {
            if (sort.isDescending()) {
                return criteriaBuilder.desc(root.get(sortField));
            }
            return criteriaBuilder.asc(root.get(sortField));
        }
        if (sort.isDescending()) {
            return criteriaBuilder.desc(root);
        }
        return criteriaBuilder.asc(root);
    }

    private Sort.Direction getDirection(String direction) {
        if (direction.equalsIgnoreCase("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }
}
