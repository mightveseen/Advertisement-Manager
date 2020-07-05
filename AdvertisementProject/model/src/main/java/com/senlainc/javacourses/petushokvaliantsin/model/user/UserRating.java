package com.senlainc.javacourses.petushokvaliantsin.model.user;

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
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_ratings")
public class UserRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_owner_id")
    private User rateOwnerUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_rated_id")
    private User ratedUser;
    @Column(name = "rating_value")
    private Short value;

    public UserRating(User rateOwnerUser, User ratedUser, Short value) {
        this.rateOwnerUser = rateOwnerUser;
        this.ratedUser = ratedUser;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRating that = (UserRating) o;
        return getId().equals(that.getId()) &&
                getRateOwnerUser().equals(that.getRateOwnerUser()) &&
                getRatedUser().equals(that.getRatedUser()) &&
                getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRateOwnerUser(), getRatedUser(), getValue());
    }

    @Override
    public String toString() {
        return "UserRating{" +
                "id=" + id +
                ", rateOwnerUser=" + rateOwnerUser +
                ", ratedUser=" + ratedUser +
                ", value=" + value +
                '}';
    }
}
