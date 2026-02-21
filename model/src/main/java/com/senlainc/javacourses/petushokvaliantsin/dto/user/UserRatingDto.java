package com.senlainc.javacourses.petushokvaliantsin.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.io.Serializable;

public record UserRatingDto(
        @Null(groups = Create.class) Long id,
        @Null(groups = Create.class) UserDto rateOwnerUser,
        @Null(groups = Create.class) UserDto ratedUser,
        @NotNull(groups = Create.class) Short value
) implements Serializable {

    public interface Create {
    }
}
