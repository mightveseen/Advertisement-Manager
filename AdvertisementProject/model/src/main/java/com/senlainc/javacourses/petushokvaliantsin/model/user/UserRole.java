package com.senlainc.javacourses.petushokvaliantsin.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long index;
    @Column(name = "role_description")
    private String description;

    public UserRole() {
    }

    public UserRole(String description) {
        this.description = description;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return index.equals(userRole.index) &&
                description.equals(userRole.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, description);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "index=" + index +
                ", description='" + description + '\'' +
                '}';
    }
}
