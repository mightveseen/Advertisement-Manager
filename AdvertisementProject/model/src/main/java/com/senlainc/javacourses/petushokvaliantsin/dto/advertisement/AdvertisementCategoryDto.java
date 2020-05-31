package com.senlainc.javacourses.petushokvaliantsin.dto.advertisement;

import java.io.Serializable;

public class AdvertisementCategoryDto implements Serializable {

    private Long index;
    private String description;

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}