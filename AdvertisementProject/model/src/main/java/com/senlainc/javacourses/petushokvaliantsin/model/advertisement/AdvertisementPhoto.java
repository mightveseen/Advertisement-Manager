package com.senlainc.javacourses.petushokvaliantsin.model.advertisement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "advertisement_photos")
public class AdvertisementPhoto {

    @Id
    @Column(name = "photo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id", nullable = false)
    private Advertisement advertisement;
    @Column(name = "photo_url")
    private String url;

    public AdvertisementPhoto() {
    }

    public AdvertisementPhoto(Advertisement advertisement, String url) {
        this.advertisement = advertisement;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdvertisementPhoto that = (AdvertisementPhoto) o;
        return id.equals(that.id) &&
                advertisement.equals(that.advertisement) &&
                url.equals(that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, advertisement, url);
    }
}
