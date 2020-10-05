package com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation;

import com.senlainc.javacourses.petushokvaliantsin.utility.page.IPageParameter;
import lombok.Getter;
import org.springframework.data.domain.Sort;

import javax.persistence.metamodel.SingularAttribute;

@Getter
public final class PageParameter implements IPageParameter {

    private final Sort.Direction direction;
    private final int firstElement;
    private final int maxResult;
    private SingularAttribute[] criteriaField;

    private PageParameter(int page, int numberElements) {
        this.firstElement = setFirstElement(page, numberElements);
        this.maxResult = setMaxResult(page, numberElements);
        this.direction = Sort.Direction.ASC;
    }

    private PageParameter(int page, int numberElements, String direction) {
        this.firstElement = setFirstElement(page, numberElements);
        this.maxResult = setMaxResult(page, numberElements);
        this.direction = setDirection(direction);
    }

    @SafeVarargs
    private <E, F> PageParameter(int page, int numberElements, String direction, SingularAttribute<E, F>... criteriaField) {
        this.firstElement = setFirstElement(page, numberElements);
        this.maxResult = setMaxResult(page, numberElements);
        this.direction = setDirection(direction);
        this.criteriaField = criteriaField;
    }

    public static IPageParameter of(int page, int numberElements) {
        return new PageParameter(page, numberElements);
    }

    public static IPageParameter of(int page, int numberElements, String direction) {
        return new PageParameter(page, numberElements, direction);
    }

    @SafeVarargs
    public static <E, F> IPageParameter of(int page, int numberElements, String direction, SingularAttribute<E, F>... criteriaField) {
        return new PageParameter(page, numberElements, direction, criteriaField);
    }

    private Sort.Direction setDirection(String direction) {
        return direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
    }

    private int setFirstElement(int page, int numberElements) {
        return page <= 0 ? 0 : page * numberElements - numberElements;
    }

    private int setMaxResult(int page, int numberElements) {
        return page <= 0 ? numberElements : page * numberElements;
    }
}
