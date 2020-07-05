package com.senlainc.javacourses.petushokvaliantsin.dto.chat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class MessageDto implements Serializable {

    @Null(groups = Create.class)
    private Long id;
    @Null(groups = Create.class)
    private UserDto user;
    @NotEmpty(groups = Create.class)
    private String text;
    @Null(groups = Create.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm+dd-MM-yyyy")
    private LocalDateTime dateTime;

    public interface Create {
    }
}
