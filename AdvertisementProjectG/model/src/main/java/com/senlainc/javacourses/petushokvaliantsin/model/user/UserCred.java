package com.senlainc.javacourses.petushokvaliantsin.model.user;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumRole;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "user_creds")
@NamedEntityGraph(name = GraphProperty.USER_CRED_DEFAULT,
        attributeNodes = @NamedAttributeNode(value = "user"))
public class UserCred {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private EnumRole role;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "userCred", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    public UserCred(String username, String password, EnumRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
