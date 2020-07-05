package com.senlainc.javacourses.petushokvaliantsin.model.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
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
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "advertisement_comments")
public class AdvertisementComment {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id", nullable = false)
    private Advertisement advertisement;
    @Column(name = "comment_message")
    private String message;
    @Column(name = "comment_date", updatable = false)
    private LocalDateTime dateTime;

    public AdvertisementComment(User user, Advertisement advertisement, String message, LocalDateTime dateTime) {
        this.user = user;
        this.advertisement = advertisement;
        this.message = message;
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdvertisementComment that = (AdvertisementComment) o;
        return getId().equals(that.getId()) &&
                getUser().equals(that.getUser()) &&
                getAdvertisement().equals(that.getAdvertisement()) &&
                getMessage().equals(that.getMessage()) &&
                getDateTime().equals(that.getDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getAdvertisement(), getMessage(), getDateTime());
    }

    @Override
    public String toString() {
        return "AdvertisementComment{" +
                "id=" + id +
                ", user=" + user +
                ", advertisement=" + advertisement +
                ", message='" + message + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
