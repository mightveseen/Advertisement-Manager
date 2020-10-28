package com.senlainc.javacourses.petushokvaliantsin.model.user;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Message;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "users")
@NamedEntityGraphs(value = {
        @NamedEntityGraph(name = GraphProperty.User.DEFAULT,
                attributeNodes = @NamedAttributeNode("userCred")),
        @NamedEntityGraph(name = GraphProperty.User.USER_CRED_AND_RATE,
                attributeNodes = {
                        @NamedAttributeNode(value = "userCred"),
                        @NamedAttributeNode(value = "rateOwnerUserRatings"),
                        @NamedAttributeNode(value = "ratedUserRatings")})
})
public class User {

    @Id
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private UserCred userCred;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private Integer phone;
    @Column(name = "registration_date", updatable = false)
    private LocalDate registrationDate;
    @Column(name = "rating")
    private Float rating;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<Chat> chats;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Message> messages;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<AdvertisementComment> comments;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Advertisement> advertisements;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Payment> payments;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "rateOwnerUser", fetch = FetchType.LAZY)
    private Set<UserRating> rateOwnerUserRatings;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "ratedUser", fetch = FetchType.LAZY)
    private Set<UserRating> ratedUserRatings;

    public User(String firstName, String lastName, String email, Integer phone, LocalDate registrationDate, Float rating) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.registrationDate = registrationDate;
        this.rating = rating;
    }
}
