package com.senlainc.javacourses.petushokvaliantsin.dto.advertisement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.senlainc.javacourses.petushokvaliantsin.dto.StateDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AdvertisementCategoryDto getAdvertisementCategory() {
        return advertisementCategory;
    }

    public void setAdvertisementCategory(AdvertisementCategoryDto advertisementCategory) {
        this.advertisementCategory = advertisementCategory;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public StateDto getState() {
        return state;
    }

    public void setState(StateDto state) {
        this.state = state;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public interface Create {

    }

    public interface Update {

    }

    public interface Read {

    }
}
