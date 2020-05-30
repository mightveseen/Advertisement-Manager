package com.senlainc.javacourses.petushokvaliantsin.model.user;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.AdvertisementComment;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Chat;
import com.senlainc.javacourses.petushokvaliantsin.model.chat.Message;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id", unique = true, updatable = false, nullable = false)
    private Long index;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
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
    @Column(name = "user_registration_date")
    private LocalDate registrationDate;
    @Column(name = "user_rating")
    private Float rating;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Message> messages;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<AdvertisementComment> comments;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Advertisement> advertisements;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "chat_users", joinColumns = @JoinColumn(name = "chat_user"),
            inverseJoinColumns = @JoinColumn(name = "chat_id"))
    private Set<Chat> chats;

    public User() {
    }

    public User(String firstName, String lastName, String email, Integer phone, LocalDate registrationDate, Float rating, UserCred userCred) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.registrationDate = registrationDate;
        this.rating = rating;
        this.userCred = userCred;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<AdvertisementComment> getComments() {
        return comments;
    }

    public void setComments(Set<AdvertisementComment> comments) {
        this.comments = comments;
    }

    public Set<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(Set<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }

    public Set<Chat> getChats() {
        return chats;
    }

    public void setChats(Set<Chat> chats) {
        this.chats = chats;
    }

    public UserCred getUserCred() {
        return userCred;
    }

    public void setUserCred(UserCred userCred) {
        this.userCred = userCred;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return index.equals(user.index) &&
                firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                email.equals(user.email) &&
                phone.equals(user.phone) &&
                registrationDate.equals(user.registrationDate) &&
                rating.equals(user.rating) &&
                Objects.equals(messages, user.messages) &&
                Objects.equals(comments, user.comments) &&
                Objects.equals(advertisements, user.advertisements) &&
                Objects.equals(chats, user.chats) &&
                userCred.equals(user.userCred);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, firstName, lastName, email, phone, registrationDate, rating, messages, comments, advertisements, chats, userCred);
    }

    @Override
    public String toString() {
        return "User{" +
                "index=" + index +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", registrationDate=" + registrationDate +
                ", rating=" + rating +
                '}';
    }
}
