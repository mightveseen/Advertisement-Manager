package com.senlainc.javacourses.petushokvaliantsin.dto.chat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class ChatDto implements Serializable {

    @Null(groups = Create.class)
    private Long id;
    @Null(groups = Create.class)
    private String name;
    @Null(groups = Create.class)
    private String lastMessage;
    @Null(groups = Create.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime updateDateTime;
    @Null(groups = Create.class)
    private Set<UserDto> users;

    public interface Create {
    }
}
