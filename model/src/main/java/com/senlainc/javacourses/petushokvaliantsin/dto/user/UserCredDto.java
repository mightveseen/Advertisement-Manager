package com.senlainc.javacourses.petushokvaliantsin.dto.user;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.io.Serializable;

public record UserCredDto(
        @Null(groups = Create.class) Long id,
        @NotNull(groups = Create.class) String username,
        @NotNull(groups = Create.class) String password,
        @Null(groups = Create.class) EnumRole enumRole
) implements Serializable {

    public interface Create {
    }
}
