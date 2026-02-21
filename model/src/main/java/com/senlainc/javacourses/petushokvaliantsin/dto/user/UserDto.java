package com.senlainc.javacourses.petushokvaliantsin.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.time.LocalDate;

public record UserDto(
        @Null(groups = Create.class)
        @Positive(groups = Update.class)
        @NotNull(groups = Update.class)
        Long id,
        @NotEmpty(groups = {Create.class, Update.class}) String firstName,
        @NotEmpty(groups = {Create.class, Update.class}) String lastName,
        @Email(groups = {Create.class, Update.class})
        @NotEmpty(groups = {Create.class, Update.class})
        String email,
        @NotNull(groups = {Create.class, Update.class}) Integer phone,
        @Null(groups = {Create.class, Update.class})
        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalDate registrationDate,
        @Null(groups = {Create.class, Update.class}) Float rating
) implements Serializable {

    public interface Create {
    }

    public interface Update {
    }
}
