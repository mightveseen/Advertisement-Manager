package com.senlainc.javacourses.petushokvaliantsin.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Getter
@Setter
public class StateDto implements Serializable {

    @Null(groups = Read.class)
    private Long id;
    @NotNull(groups = Read.class)
    private String description;

    public interface Read {
    }
}
