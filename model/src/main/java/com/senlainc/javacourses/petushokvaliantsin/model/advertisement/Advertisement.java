package com.senlainc.javacourses.petushokvaliantsin.model.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.State;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User_;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "advertisements")
@NamedEntityGraphs(value = {
        @NamedEntityGraph(name = GraphProperty.Advertisement.DEFAULT, attributeNodes = {
                @NamedAttributeNode(value = Advertisement_.USER, subgraph = "Advertisement.user.userCred"),
                @NamedAttributeNode(Advertisement_.CATEGORY),
                @NamedAttributeNode(Advertisement_.STATE)},
                subgraphs = @NamedSubgraph(name = "Advertisement.user.userCred",
                        attributeNodes = @NamedAttributeNode(User_.USER_CRED))),
        @NamedEntityGraph(name = GraphProperty.Advertisement.USER,
                attributeNodes = @NamedAttributeNode(Advertisement_.USER))
})
public class Advertisement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "header")
    private String header;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", updatable = false)
    @ToString.Exclude
    private User user;
    @Column(name = "description")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private AdvertisementCategory category;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "price")
    private Double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    @ToString.Exclude
    private State state;

    @ToString.Exclude
    @OneToMany(mappedBy = "advertisement", fetch = FetchType.LAZY)
    private Set<AdvertisementComment> comments;
    @ToString.Exclude
    @OneToMany(mappedBy = "advertisement", fetch = FetchType.LAZY)
    private Set<AdvertisementPhoto> photos;
    @ToString.Exclude
    @OneToMany(mappedBy = "advertisement", fetch = FetchType.LAZY)
    private Set<Payment> payments;

    public Advertisement(String header, User user, String description, AdvertisementCategory category, LocalDate date, State state) {
        this.header = header;
        this.user = user;
        this.description = description;
        this.category = category;
        this.date = date;
        this.state = state;
    }
}
