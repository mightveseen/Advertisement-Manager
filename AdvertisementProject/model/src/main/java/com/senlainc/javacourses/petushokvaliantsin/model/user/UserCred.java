package com.senlainc.javacourses.petushokvaliantsin.model.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "user_data")
public class UserCred {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long index;
    @Column(name = "user_login")
    private String login;
    @Column(name = "user_password")
    private String password;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private UserRole userRole;
    @OneToOne(mappedBy = "userCred", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    public UserCred() {
    }

    public UserCred(String login, String password) {
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
        UserCred userCred = (UserCred) o;
        return index.equals(userCred.index) &&
                login.equals(userCred.login) &&
                password.equals(userCred.password) &&
                Objects.equals(user, userCred.user);
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
                '}';
    }
}
