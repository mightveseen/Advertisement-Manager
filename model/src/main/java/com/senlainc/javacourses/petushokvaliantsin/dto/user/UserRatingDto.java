package com.senlainc.javacourses.petushokvaliantsin.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Getter
@Setter
public class UserRatingDto implements Serializable {

    @Null(groups = Create.class)
    private Long id;
    @Null(groups = Create.class)
    private UserDto rateOwnerUser;
    @Null(groups = Create.class)
    private UserDto ratedUser;
    @NotNull(groups = Create.class)
    private Short value;

    public interface Create {

    }
}
