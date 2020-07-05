package com.senlainc.javacourses.petushokvaliantsin.model.user;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Message;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id", unique = true, updatable = false, nullable = false)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserCred userCred;
    @Column(name = "user_first_name")
    private String firstName;
    @Column(name = "user_last_name")
    private String lastName;
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_phone")
    private Integer phone;
    @Column(name = "user_registration_date", updatable = false)
    private LocalDate registrationDate;
    @Column(name = "user_rating")
    private Float rating;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<Chat> chats;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Message> messages;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<AdvertisementComment> comments;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Advertisement> advertisements;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Payment> payments;
    @OneToMany(mappedBy = "rateOwnerUser", fetch = FetchType.LAZY)
    private Set<UserRating> rateOwnerUserRatings;
    @OneToMany(mappedBy = "ratedUser", fetch = FetchType.LAZY)
    private Set<UserRating> ratedUserRatings;

    public User(String firstName, String lastName, String email, Integer phone, LocalDate registrationDate, UserCred userCred) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.registrationDate = registrationDate;
        this.userCred = userCred;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId().equals(user.getId()) &&
                getUserCred().equals(user.getUserCred()) &&
                getFirstName().equals(user.getFirstName()) &&
                getLastName().equals(user.getLastName()) &&
                getEmail().equals(user.getEmail()) &&
                getPhone().equals(user.getPhone()) &&
                getRegistrationDate().equals(user.getRegistrationDate()) &&
                getRating().equals(user.getRating());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserCred(), getFirstName(), getLastName(), getEmail(), getPhone(), getRegistrationDate(), getRating());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userCred=" + userCred +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", registrationDate=" + registrationDate +
                ", rating=" + rating +
                '}';
    }
}
