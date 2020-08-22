package com.senlainc.javacourses.petushokvaliantsin.model.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.graph.GraphName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "advertisement_photos")
@NamedEntityGraph(name = GraphName.ADVERTISEMENT_PHOTO_DEFAULT, attributeNodes = @NamedAttributeNode("advertisement"))
public class AdvertisementPhoto {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id", nullable = false)
    private Advertisement advertisement;
    @Column(name = "url")
    private String url;

    public AdvertisementPhoto(Advertisement advertisement, String url) {
        this.advertisement = advertisement;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdvertisementPhoto that = (AdvertisementPhoto) o;
        return getId().equals(that.getId()) &&
                getAdvertisement().equals(that.getAdvertisement()) &&
                getUrl().equals(that.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAdvertisement(), getUrl());
    }

    @Override
    public String toString() {
        return "AdvertisementPhoto{" +
                "id=" + id +
                ", advertisement=" + advertisement +
                ", url='" + url + '\'' +
                '}';
    }
}
