package com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation;

import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;
import org.springframework.data.domain.Sort;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

public class PageParameter implements IPageParameter {

    private final int firstElement;
    private final int maxResult;
    private final Sort.Direction sort;
    private SingularAttribute criteriaField;

    private PageParameter(int page, int numberElements) {
        this.firstElement = getFirstElement(page, numberElements);
        this.maxResult = getMaxResult(page, numberElements);
        this.sort = Sort.Direction.ASC;
    }

    private PageParameter(int page, int numberElements, String direction) {
        this.firstElement = getFirstElement(page, numberElements);
        this.maxResult = getMaxResult(page, numberElements);
        this.sort = getDirection(direction);
    }

    private <E, F> PageParameter(int page, int numberElements, String direction, SingularAttribute<E, F> criteriaField) {
        this.firstElement = getFirstElement(page, numberElements);
        this.maxResult = getMaxResult(page, numberElements);
        this.sort = getDirection(direction);
        this.criteriaField = criteriaField;
    }

    public static PageParameter of(int page, int numberElements) {
        return new PageParameter(page, numberElements);
    }

    public static PageParameter of(int page, int numberElements, String direction) {
        return new PageParameter(page, numberElements, direction);
    }

    public static <E, F> PageParameter of(int page, int numberElements, String direction, SingularAttribute<E, F> criteriaField) {
        return new PageParameter(page, numberElements, direction, criteriaField);
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
    public <E, F> SingularAttribute<E, F> getCriteriaField() {
        return criteriaField;
    }

    @Override
    public <E> Order getOrder(CriteriaBuilder criteriaBuilder, Root<E> root) {
        switch (sort) {
            case ASC:
                return criteriaField != null ? criteriaBuilder.asc(root.get(criteriaField)) : criteriaBuilder.asc(root);
            case DESC:
                return criteriaField != null ? criteriaBuilder.desc(root.get(criteriaField)) : criteriaBuilder.desc(root);
            default:
                return criteriaBuilder.asc(root);
        }
    }

    private Sort.Direction getDirection(String direction) {
        if (direction.equalsIgnoreCase("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    private int getFirstElement(int page, int numberElements) {
        return page <= 0 ? 0 : page * numberElements - numberElements;
    }

    private int getMaxResult(int page, int numberElements) {
        return page <= 0 ? numberElements : page * numberElements;
    }
}
