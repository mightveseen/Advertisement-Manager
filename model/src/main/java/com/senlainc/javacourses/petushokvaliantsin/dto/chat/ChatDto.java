package com.senlainc.javacourses.petushokvaliantsin.dto.chat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import jakarta.validation.constraints.Null;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

public record ChatDto(
        @Null(groups = Create.class) Long id,
        @Null(groups = Create.class) String name,
        @Null(groups = Create.class) String lastMessage,
        @Null(groups = Create.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm dd-MM-yyyy")
        LocalDateTime updateDateTime,
        @Null(groups = Create.class) Set<UserDto> users
) implements Serializable {

    public interface Create {
    }
}
