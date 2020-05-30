package com.senlainc.javacourses.petushokvaliantsin.dto.user;

import java.io.Serializable;

public class UserCredDto implements Serializable {

    private Long index;
    private String login;
    private String password;
    private UserRoleDto userRole;
    private UserDto user;

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
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

    public UserRoleDto getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleDto userRole) {
        this.userRole = userRole;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
