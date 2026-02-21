package com.senlainc.javacourses.petushokvaliantsin.dto.advertisement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.senlainc.javacourses.petushokvaliantsin.dto.StateDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;
import java.time.LocalDate;

public record AdvertisementDto(
        @Null(groups = Create.class)
        @Positive(groups = Update.class)
        @NotNull(groups = Update.class)
        Long id,
        @NotEmpty(groups = {Create.class, Update.class}) String header,
        @Null(groups = {Update.class, Create.class}) UserDto user,
        @NotEmpty(groups = {Create.class, Update.class}) String description,
        @Valid @NotNull(groups = {Create.class, Update.class}) AdvertisementCategoryDto advertisementCategory,
        @Null(groups = {Create.class, Update.class})
        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalDate date,
        @NotNull(groups = {Create.class, Update.class})
        @PositiveOrZero(groups = {Create.class, Update.class})
        Double price,
        @Null(groups = {Create.class, Update.class}) StateDto state
) implements Serializable {

    public interface Create {
    }

    public interface Update {
    }
}
