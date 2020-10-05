package com.senlainc.javacourses.petushokvaliantsin.model.payment;

import com.senlainc.javacourses.petushokvaliantsin.enumeration.GraphProperty;
import com.senlainc.javacourses.petushokvaliantsin.model.State;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
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
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
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
