package com.senlainc.javacourses.petushokvaliantsin.model.advertisement;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.State;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User_;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
import javax.persistence.NamedQuery;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
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
@NamedQuery(name = "Advertisement.readAllWithUser", query = "select a from Advertisement a\n" +
        "left join a.user u \n" +
        "left join a.state s \n" +
        "where u = :user and s = :state")
public class Advertisement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "header")
    private String header;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", updatable = false)
    private User user;
    @Column(name = "description")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private AdvertisementCategory category;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "price")
    private Double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private State state;

    @OneToMany(mappedBy = "advertisement", fetch = FetchType.LAZY)
    private Set<AdvertisementComment> comments;
    @OneToMany(mappedBy = "advertisement", fetch = FetchType.LAZY)
    private Set<AdvertisementPhoto> photos;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return getId().equals(that.getId()) &&
                getHeader().equals(that.getHeader()) &&
                getUser().equals(that.getUser()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                getCategory().equals(that.getCategory()) &&
                getDate().equals(that.getDate()) &&
                getPrice().equals(that.getPrice()) &&
                getState().equals(that.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getHeader(), getUser(), getDescription(), getCategory(), getDate(), getPrice(), getState());
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", date=" + date +
                ", price=" + price +
                ", state=" + state +
                '}';
    }
}
