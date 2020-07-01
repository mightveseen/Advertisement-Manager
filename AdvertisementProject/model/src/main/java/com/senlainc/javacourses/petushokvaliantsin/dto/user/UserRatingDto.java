package com.senlainc.javacourses.petushokvaliantsin.dto.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

public class UserRatingDto implements Serializable {

    @Null(groups = Create.class)
    private Long id;
    @Null(groups = Create.class)
    private UserDto rateOwnerUser;
    @Null(groups = Create.class)
    private UserDto ratedUser;
    @NotNull(groups = Create.class)
    private Short value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getRateOwnerUser() {
        return rateOwnerUser;
    }

    public void setRateOwnerUser(UserDto rateOwnerUser) {
        this.rateOwnerUser = rateOwnerUser;
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

    public interface Create {

    }
}
