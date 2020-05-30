package com.senlainc.javacourses.petushokvaliantsin.dto.payment;

import java.io.Serializable;

public class PaymentTypeDto implements Serializable {

    private Long index;
    private String description;
    private Integer duration;
    private Double price;

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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
