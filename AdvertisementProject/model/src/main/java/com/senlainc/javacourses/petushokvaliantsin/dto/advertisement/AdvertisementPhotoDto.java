package com.senlainc.javacourses.petushokvaliantsin.dto.advertisement;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

public class AdvertisementPhotoDto implements Serializable {

    @Null(groups = {Create.class})
    private Long id;
    @NotNull(groups = {Create.class})
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public interface Create {
    }
}
