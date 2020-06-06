package com.senlainc.javacourses.petushokvaliantsin.dto.user;

import java.io.Serializable;

public class UserRatingDto implements Serializable {

    private Long id;
    private UserDto userOwner;
    private UserDto ratedUser;
    private Short value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(UserDto userOwner) {
        this.userOwner = userOwner;
    }

    public UserDto getRatedUser() {
        return ratedUser;
    }

    public void setRatedUser(UserDto ratedUser) {
        this.ratedUser = ratedUser;
    }

    public Short getValue() {
        return value;
    }

    public void setValue(Short value) {
        this.value = value;
    }
}
