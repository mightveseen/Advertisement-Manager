package com.senlainc.javacourses.petushokvaliantsin.dto.advertisement;

import java.io.Serializable;

public class AdvertisementPhotoDto implements Serializable {

    private Long index;
    private AdvertisementDto advertisement;
    private String url;

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
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
