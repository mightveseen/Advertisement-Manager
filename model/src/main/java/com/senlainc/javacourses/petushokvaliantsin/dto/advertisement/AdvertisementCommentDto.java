package com.senlainc.javacourses.petushokvaliantsin.dto.advertisement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;

import java.io.Serializable;
import java.time.LocalDateTime;

public record AdvertisementCommentDto(
        @Null(groups = Create.class) Long id,
        @Null(groups = Create.class) UserDto user,
        @NotEmpty(groups = Create.class) String message,
        @Null(groups = Create.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm+dd-MM-yyyy")
        LocalDateTime dateTime
) implements Serializable {

    public interface Create {
    }
}
