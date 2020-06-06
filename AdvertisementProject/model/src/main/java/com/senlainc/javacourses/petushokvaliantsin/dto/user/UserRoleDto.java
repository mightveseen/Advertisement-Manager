package com.senlainc.javacourses.petushokvaliantsin.dto.user;

import java.io.Serializable;

public class UserRoleDto implements Serializable {

    private Long id;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
