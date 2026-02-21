package com.senlainc.javacourses.petushokvaliantsin.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.io.Serializable;

public record StateDto(
        @Null(groups = Read.class) Long id,
        @NotNull(groups = Read.class) String description
) implements Serializable {

    public interface Read {
    }
}
