package com.senlainc.javacourses.petushokvaliantsin.model.user;

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
@Table(name = "user_ratings")
public class UserRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_owner_id")
    private User userOwner;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_rated_id")
    private User ratedUser;
    @Column(name = "rating_value")
    private Short value;

    public UserRating() {
    }

    public UserRating(User userOwner, User ratedUser, Short value) {
        this.userOwner = userOwner;
        this.ratedUser = ratedUser;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }

    public User getRatedUser() {
        return ratedUser;
    }

    public void setRatedUser(User ratedUser) {
        this.ratedUser = ratedUser;
    }

    public Short getValue() {
        return value;
    }

    public void setValue(Short value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRating that = (UserRating) o;
        return id.equals(that.id) &&
                Objects.equals(userOwner, that.userOwner) &&
                ratedUser.equals(that.ratedUser) &&
                value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userOwner, ratedUser, value);
    }
}
