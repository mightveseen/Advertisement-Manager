package com.senlainc.javacourses.petushokvaliantsin.model.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
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
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
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
