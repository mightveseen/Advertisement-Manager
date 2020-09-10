package com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation;

import com.senlainc.javacourses.petushokvaliantsin.utility.page.IFilterParameter;

public final class FilterParameter implements IFilterParameter {

    private final String category;
    private final String search;
    private final double minPrice;
    private final double maxPrice;

    private FilterParameter(String search, String category, double minPrice, double maxPrice) {
        this.search = search;
        this.category = category;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public static FilterParameter of(String search, String category, double minPrice, double maxPrice) {
        return new FilterParameter(search, category, minPrice, maxPrice);
    }

    @Override
    public String getSearch() {
        return search;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public double getMinPrice() {
        return minPrice;
    }

    @Override
    public double getMaxPrice() {
        return maxPrice;
    }
}
