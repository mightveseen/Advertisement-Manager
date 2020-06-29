package com.senlainc.javacourses.petushokvaliantsin.dto.chat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

public class ChatDto implements Serializable {

    @Positive(groups = Update.class)
    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Long id;
    @NotEmpty(groups = {Create.class, Update.class})
    private String name;
    @Null(groups = Create.class)
    @NotEmpty(groups = Update.class)
    private String lastMessage;
    @Null(groups = {Create.class, Update.class})
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime updateDateTime;
    @Valid
    @NotNull(groups = {Create.class, Update.class})
    private Set<UserDto> users;

    public Set<UserDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDto> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public interface Create {
    }

    public interface Update {
    }

    public interface Remove {
    }
}
