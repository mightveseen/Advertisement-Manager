package com.senlainc.javacourses.petushokvaliantsin.dto.advertisement;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.io.Serializable;

public class AdvertisementCategoryDto implements Serializable {

    @Null(groups = Create.class)
    @NotNull(groups = {Update.class, Read.class})
    @Positive(groups = {Update.class, Read.class})
    private Long id;
    @NotEmpty(groups = {Create.class, Update.class})
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

    public interface Create {

    }

    public interface Update {

    }

    public interface Read {

    }
}
