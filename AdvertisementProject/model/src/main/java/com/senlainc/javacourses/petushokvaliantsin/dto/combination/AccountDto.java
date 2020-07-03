package com.senlainc.javacourses.petushokvaliantsin.dto.combination;

import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserCredDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AccountDto {

    @Valid
    @NotNull(groups = Create.class)
    private UserCredDto userCred;
    @Valid
    @NotNull(groups = Create.class)
    private UserDto user;

    public UserCredDto getUserCred() {
        return userCred;
    }

    public void setUserCred(UserCredDto userCred) {
        this.userCred = userCred;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public interface Create {

    }
}
