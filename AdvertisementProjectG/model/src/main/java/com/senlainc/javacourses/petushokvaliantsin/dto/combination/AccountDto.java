package com.senlainc.javacourses.petushokvaliantsin.dto.combination;

import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserCredDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.user.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AccountDto {

    @Valid
    @NotNull(groups = Create.class)
    private UserCredDto userCred;
    @Valid
    @NotNull(groups = Create.class)
    private UserDto user;

    public interface Create {

    }
}
