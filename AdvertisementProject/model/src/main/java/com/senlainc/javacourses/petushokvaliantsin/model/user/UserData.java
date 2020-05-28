package com.senlainc.javacourses.petushokvaliantsin.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "user_data")
public class UserData {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long index;
    @Column(name = "user_login")
    private String login;
    @Column(name = "user_password")
    private String password;
    @OneToOne(mappedBy = "userData", fetch = FetchType.LAZY)
    private User user;

    public UserData() {
    }

    public UserData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return index.equals(userData.index) &&
                login.equals(userData.login) &&
                password.equals(userData.password) &&
                Objects.equals(user, userData.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, login, password, user);
    }

    @Override
    public String toString() {
        return "UserData{" +
                "index=" + index +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user +
                '}';
    }
}
