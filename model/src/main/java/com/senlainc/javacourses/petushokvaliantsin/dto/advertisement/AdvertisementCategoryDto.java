package com.senlainc.javacourses.petushokvaliantsin.dto.advertisement;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

public record AdvertisementCategoryDto(
        @Null(groups = Create.class)
        @NotNull(groups = {Update.class, Read.class})
        @Positive(groups = {Update.class, Read.class})
        Long id,
        @NotEmpty(groups = {Create.class, Update.class})
        String description
) implements Serializable {

    public interface Create {
    }

    public interface Update {
    }

    public interface Read {
    }
}
