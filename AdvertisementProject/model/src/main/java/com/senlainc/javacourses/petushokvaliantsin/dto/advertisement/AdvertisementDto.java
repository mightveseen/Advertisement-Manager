package com.senlainc.javacourses.petushokvaliantsin.dto.advertisement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.senlainc.javacourses.petushokvaliantsin.dto.StateDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class AdvertisementDto implements Serializable {

    @Null(groups = Create.class)
    @Positive(groups = Update.class)
    @NotNull(groups = Update.class)
    private Long id;
    @NotEmpty(groups = {Create.class, Update.class})
    private String header;
    @Null(groups = {Update.class, Create.class})
    private UserDto user;
    @NotEmpty(groups = {Create.class, Update.class})
    private String description;
    @Valid
    @NotNull(groups = {Create.class, Update.class})
    private AdvertisementCategoryDto advertisementCategory;
    @Null(groups = {Create.class, Update.class})
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
    @NotNull(groups = {Create.class, Update.class})
    @PositiveOrZero(groups = {Create.class, Update.class})
    private Double price;
    @Null(groups = {Create.class, Update.class})
    private StateDto state;

    public interface Create {

    }

    public interface Update {

    }

    public interface Read {

    }
}
