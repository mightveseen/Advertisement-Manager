package com.senlainc.javacourses.petushokvaliantsin.dto.user;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumRole;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Getter
@Setter
public class UserCredDto implements Serializable {

    @Null(groups = Create.class)
    private Long id;
    @NotNull(groups = Create.class)
    private String username;
    @NotNull(groups = Create.class)
    private String password;
    @Null(groups = Create.class)
    private EnumRole enumRole;

    public interface Create {

    }
}
