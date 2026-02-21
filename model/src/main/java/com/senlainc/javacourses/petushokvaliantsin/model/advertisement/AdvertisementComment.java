package com.senlainc.javacourses.petushokvaliantsin.model.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
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
import jakarta.persistence.NamedEntityGraphs;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "advertisement_comments")
@NamedEntityGraphs(value = {
        @NamedEntityGraph(name = GraphProperty.AdvertisementComment.DEFAULT, attributeNodes = {
                @NamedAttributeNode(value = "user"),
                @NamedAttributeNode(value = "advertisement")}),
        @NamedEntityGraph(name = GraphProperty.AdvertisementComment.USER,
                attributeNodes = @NamedAttributeNode(value = "user", subgraph = "user-subgraph"),
                subgraphs = @NamedSubgraph(name = "user-subgraph", attributeNodes = @NamedAttributeNode(value = "userCred")))
})
public class AdvertisementComment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id", nullable = false)
    private Advertisement advertisement;
    @Column(name = "message")
    private String message;
    @Column(name = "date", updatable = false)
    private LocalDateTime dateTime;

    public AdvertisementComment(User user, Advertisement advertisement, String message, LocalDateTime dateTime) {
        this.user = user;
        this.advertisement = advertisement;
        this.message = message;
        this.dateTime = dateTime;
    }
}
