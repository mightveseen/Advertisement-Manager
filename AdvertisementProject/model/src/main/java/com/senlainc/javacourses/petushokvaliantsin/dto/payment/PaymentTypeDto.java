package com.senlainc.javacourses.petushokvaliantsin.dto.payment;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.io.Serializable;

public class PaymentTypeDto implements Serializable {

    @Positive(groups = {Update.class, AddPayment.class})
    @Null(groups = {Create.class})
    @NotNull(groups = {Update.class, AddPayment.class})
    private Long id;
    @Null(groups = {AddPayment.class})
    @NotNull(groups = {Create.class, Update.class})
    private String description;
    @Null(groups = {AddPayment.class})
    @NotNull(groups = {Create.class, Update.class})
    private Integer duration;
    @Null(groups = {AddPayment.class})
    @NotNull(groups = {Create.class, Update.class})
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public interface Create {

    }

    public interface Update {

    }

    public interface AddPayment {

    }
}
