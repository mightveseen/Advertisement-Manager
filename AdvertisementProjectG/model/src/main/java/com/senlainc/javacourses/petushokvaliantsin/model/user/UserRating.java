package com.senlainc.javacourses.petushokvaliantsin.model.user;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_ratings")
@NamedEntityGraph(name = GraphProperty.USER_RATING_DEFAULT, attributeNodes = {
        @NamedAttributeNode(value = "rateOwnerUser", subgraph = "user-rating-subgraph"),
        @NamedAttributeNode(value = "ratedUser", subgraph = "user-rating-subgraph")},
        subgraphs = @NamedSubgraph(name = "user-rating-subgraph", attributeNodes = @NamedAttributeNode(value = "userCred")))
public class UserRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User rateOwnerUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rated_id")
    private User ratedUser;
    @Column(name = "value")
    private Short value;

    public UserRating(User rateOwnerUser, User ratedUser, Short value) {
        this.rateOwnerUser = rateOwnerUser;
        this.ratedUser = ratedUser;
        this.value = value;
    }
}
