package com.senlainc.javacourses.petushokvaliantsin.dto.advertisement;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Getter
@Setter
public class AdvertisementPhotoDto implements Serializable {

    @Null(groups = Create.class)
    private Long id;
    @NotNull(groups = Create.class)
    private String url;

    public interface Create {
    }
}
