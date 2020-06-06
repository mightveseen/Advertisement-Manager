package com.senlainc.javacourses.petushokvaliantsin.dto.advertisement;

import java.io.Serializable;

public class AdvertisementPhotoDto implements Serializable {

    private Long id;
    private AdvertisementDto advertisement;
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdvertisementDto getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(AdvertisementDto advertisement) {
        this.advertisement = advertisement;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
