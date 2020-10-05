package com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation;

import com.senlainc.javacourses.petushokvaliantsin.utility.page.IFilterParameter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public final class FilterParameter implements IFilterParameter {

    private final String category;
    private final String search;
    private final double minPrice;
    private final double maxPrice;
}
