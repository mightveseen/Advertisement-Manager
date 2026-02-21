package com.senlainc.javacourses.petushokvaliantsin.dto.advertisement;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.io.Serializable;

public record AdvertisementPhotoDto(
        @Null(groups = Create.class) Long id,
        @NotNull(groups = Create.class) String url
) implements Serializable {

    public interface Create {
    }
}
