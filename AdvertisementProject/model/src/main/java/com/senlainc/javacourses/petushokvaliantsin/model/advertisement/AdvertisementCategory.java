package com.senlainc.javacourses.petushokvaliantsin.model.advertisement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "advertisement_categories")
public class AdvertisementCategory {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long index;
    @Column(name = "category_description")
    private String description;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Advertisement> advertisements;

    public AdvertisementCategory() {
    }

    public AdvertisementCategory(String description) {
        this.description = description;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdvertisementCategory that = (AdvertisementCategory) o;
        return index.equals(that.index) &&
                description.equals(that.description) &&
                Objects.equals(advertisements, that.advertisements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, description, advertisements);
    }

    @Override
    public String toString() {
        return "AdvertisementCategory{" +
                "index=" + index +
                ", description='" + description + '\'' +
                ", advertisements=" + advertisements +
                '}';
    }
}
