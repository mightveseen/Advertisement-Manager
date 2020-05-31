package com.senlainc.javacourses.petushokvaliantsin.dto.advertisement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AdvertisementCommentDto implements Serializable {

    private Long index;
    private UserDto user;
    private AdvertisementDto advertisement;
    private String message;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm+dd-MM-yyyy")
    private LocalDateTime dateTime;

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public AdvertisementDto getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(AdvertisementDto advertisement) {
        this.advertisement = advertisement;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}