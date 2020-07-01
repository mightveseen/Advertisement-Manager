package com.senlainc.javacourses.petushokvaliantsin.dto.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

public class UserCredDto implements Serializable {

    @Null(groups = Create.class)
    private Long id;
    @NotNull(groups = Create.class)
    private String login;
    @NotNull(groups = Create.class)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public interface Create {

    }
}
