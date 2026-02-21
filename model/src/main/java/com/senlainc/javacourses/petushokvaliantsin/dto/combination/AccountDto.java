package com.senlainc.javacourses.petushokvaliantsin.dto.combination;

import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserCredDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record AccountDto(
        @Valid @NotNull(groups = Create.class) UserCredDto userCred,
        @Valid @NotNull(groups = Create.class) UserDto user
) {

    public interface Create {
    }
}
