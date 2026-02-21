package com.senlainc.javacourses.petushokvaliantsin.model.payment;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.State;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
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
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "payments")
@NamedEntityGraph(name = GraphProperty.PAYMENT_DEFAULT, attributeNodes = {
        @NamedAttributeNode(value = "user"),
        @NamedAttributeNode(value = "advertisement", subgraph = "payment-advertisement-subgraph"),
        @NamedAttributeNode(value = "type"),
        @NamedAttributeNode(value = "state")},
        subgraphs = @NamedSubgraph(name = "payment-advertisement-subgraph",
                attributeNodes = {@NamedAttributeNode(value = "category"), @NamedAttributeNode(value = "state")})
)
public class Payment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id", nullable = false)
    private Advertisement advertisement;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private PaymentType type;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "price")
    private Double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private State state;

    public Payment(User user, Advertisement advertisement, PaymentType type, LocalDate startDate, LocalDate endDate, Double price, State state) {
        this.user = user;
        this.advertisement = advertisement;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.state = state;
    }
}
