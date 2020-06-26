package com.senlainc.javacourses.petushokvaliantsin.dto.advertisement;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.io.Serializable;

public class AdvertisementCategoryDto implements Serializable {

    //TODO : Validation not working while add Advertisement
    @Null(groups = {Create.class})
    @NotNull(groups = {Update.class, AdvertisementDto.Update.class, AdvertisementDto.Create.class})
    @Positive(groups = {Update.class, AdvertisementDto.Update.class, AdvertisementDto.Create.class})
    private Long id;
    @NotNull(groups = {Create.class, Update.class})
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
}
