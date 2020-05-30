package com.senlainc.javacourses.petushokvaliantsin.model.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "advertisement_comments")
public class AdvertisementComment {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long index;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id", nullable = false)
    private Advertisement advertisement;
    @Column(name = "comment_message")
    private String message;
    @Column(name = "comment_date")
    private LocalDateTime dateTime;

    public AdvertisementComment() {
    }

    public AdvertisementComment(User user, Advertisement advertisement, String message, LocalDateTime dateTime) {
        this.user = user;
        this.advertisement = advertisement;
        this.message = message;
        this.dateTime = dateTime;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdvertisementComment that = (AdvertisementComment) o;
        return index.equals(that.index) &&
                Objects.equals(user, that.user) &&
                advertisement.equals(that.advertisement) &&
                message.equals(that.message) &&
                dateTime.equals(that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, user, advertisement, message, dateTime);
    }

    @Override
    public String toString() {
        return "AdvertisementComment{" +
                "index=" + index +
                ", user=" + user +
                ", advertisement=" + advertisement +
                ", message='" + message + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
