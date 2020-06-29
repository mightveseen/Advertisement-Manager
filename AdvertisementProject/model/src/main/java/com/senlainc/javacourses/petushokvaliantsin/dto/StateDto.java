package com.senlainc.javacourses.petushokvaliantsin.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

public class StateDto implements Serializable {

    @Null(groups = Read.class)
    private Long id;
    @NotNull(groups = Read.class)
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

    public interface Read {
    }
}
