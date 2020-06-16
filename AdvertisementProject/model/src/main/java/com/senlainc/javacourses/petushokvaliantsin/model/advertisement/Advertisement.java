package com.senlainc.javacourses.petushokvaliantsin.model.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.State;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;

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
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "advertisements")
@NamedEntityGraphs(value = {@NamedEntityGraph(name = "main", attributeNodes = {
        @NamedAttributeNode("id"), @NamedAttributeNode("header"), @NamedAttributeNode("description")
})})
public class Advertisement implements Cloneable {

    @Id
    @Column(name = "advertisement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "advertisement_header")
    private String header;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "advertisement_description")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private AdvertisementCategory category;
    @Column(name = "advertisement_date")
    private LocalDate date;
    @Column(name = "advertisement_price")
    private Double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private State state;

    @OneToMany(mappedBy = "advertisement", fetch = FetchType.LAZY)
    private Set<AdvertisementComment> comments;
    @OneToMany(mappedBy = "advertisement", fetch = FetchType.LAZY)
    private Set<AdvertisementPhoto> photos;
    @OneToMany(mappedBy = "advertisement", fetch = FetchType.LAZY)
    private Set<Payment> payments;

    public Advertisement() {
    }

    public Advertisement(String header, User user, String description, AdvertisementCategory category, LocalDate date, State state) {
        this.header = header;
        this.user = user;
        this.description = description;
        this.category = category;
        this.date = date;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AdvertisementCategory getCategory() {
        return category;
    }

    public void setCategory(AdvertisementCategory category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Set<AdvertisementComment> getComments() {
        return comments;
    }

    public void setComments(Set<AdvertisementComment> comments) {
        this.comments = comments;
    }

    public Set<AdvertisementPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<AdvertisementPhoto> photos) {
        this.photos = photos;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return id.equals(that.id) &&
                header.equals(that.header) &&
                user.equals(that.user) &&
                Objects.equals(description, that.description) &&
                Objects.equals(category, that.category) &&
                date.equals(that.date) &&
                price.equals(that.price) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, header, user, description, category, date, price, state);
    }

    @Override
    public Advertisement clone() throws CloneNotSupportedException {
        return (Advertisement) super.clone();
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", date=" + date +
                ", price=" + price +
                ", state=" + state + '}';
    }
}
