package com.senlainc.javacourses.petushokvaliantsin.model.user;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_creds")
public class UserCred {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_username")
    private String username;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_role")
    @Enumerated(value = EnumType.STRING)
    private EnumRole enumRole;

    @OneToOne(mappedBy = "userCred", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    public UserCred(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCred userCred = (UserCred) o;
        return getId().equals(userCred.getId()) &&
                getUsername().equals(userCred.getUsername()) &&
                getPassword().equals(userCred.getPassword()) &&
                getEnumRole() == userCred.getEnumRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getEnumRole());
    }

    @Override
    public String toString() {
        return "UserCred{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enumRole=" + enumRole +
                '}';
    }
}
