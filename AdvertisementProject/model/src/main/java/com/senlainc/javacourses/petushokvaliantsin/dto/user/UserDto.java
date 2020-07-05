package com.senlainc.javacourses.petushokvaliantsin.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class UserDto implements Serializable {

    @Null(groups = Create.class)
    @Positive(groups = Update.class)
    @NotNull(groups = Update.class)
    private Long id;
    @NotEmpty(groups = {Create.class, Update.class})
    private String firstName;
    @NotEmpty(groups = {Create.class, Update.class})
    private String lastName;
    @Email(groups = {Create.class, Update.class})
    @NotEmpty(groups = {Create.class, Update.class})
    private String email;
    @NotNull(groups = {Create.class, Update.class})
    private Integer phone;
    @Null(groups = {Create.class, Update.class})
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate registrationDate;
    @Null(groups = {Create.class, Update.class})
    private Float rating;

    public interface Create {

    }

    public interface Update {

    }
}
