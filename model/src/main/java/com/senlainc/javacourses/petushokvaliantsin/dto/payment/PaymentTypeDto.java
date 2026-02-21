package com.senlainc.javacourses.petushokvaliantsin.dto.payment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

public record PaymentTypeDto(
        @Null(groups = Create.class)
        @Positive(groups = {Update.class, Read.class})
        @NotNull(groups = {Update.class, Read.class})
        Long id,
        @NotEmpty(groups = {Create.class, Update.class}) String description,
        @NotNull(groups = {Create.class, Update.class}) Integer duration,
        @NotNull(groups = {Create.class, Update.class}) Double price
) implements Serializable {

    public interface Create {
    }

    public interface Update {
    }

    public interface Read {
    }
}
