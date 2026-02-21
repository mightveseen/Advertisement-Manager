package com.senlainc.javacourses.petushokvaliantsin.model.user;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
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
