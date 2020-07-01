package com.senlainc.javacourses.petushokvaliantsin.dto.user;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumRole;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

public class UserCredDto implements Serializable {

    @Null(groups = Create.class)
    private Long id;
    @NotNull(groups = Create.class)
    private String username;
    @NotNull(groups = Create.class)
    private String password;
    @Null(groups = Create.class)
    private EnumRole enumRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EnumRole getEnumRole() {
        return enumRole;
    }

    public void setEnumRole(EnumRole enumRole) {
        this.enumRole = enumRole;
    }

    public interface Create {

    }
}
