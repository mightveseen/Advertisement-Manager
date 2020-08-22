package com.senlainc.javacourses.petushokvaliantsin.model.payment;

import com.senlainc.javacourses.petushokvaliantsin.graph.DefaultGraph;
import com.senlainc.javacourses.petushokvaliantsin.graph.GraphName;
import com.senlainc.javacourses.petushokvaliantsin.model.State;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.user.User;
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
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payments")
@DefaultGraph(name = GraphName.PAYMENT_DEFAULT)
@NamedEntityGraph(name = GraphName.PAYMENT_DEFAULT, attributeNodes = {
        @NamedAttributeNode("user"), @NamedAttributeNode("advertisement"), @NamedAttributeNode("type"), @NamedAttributeNode("state")
})
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return getId().equals(payment.getId()) &&
                getUser().equals(payment.getUser()) &&
                getAdvertisement().equals(payment.getAdvertisement()) &&
                getType().equals(payment.getType()) &&
                getStartDate().equals(payment.getStartDate()) &&
                getEndDate().equals(payment.getEndDate()) &&
                getPrice().equals(payment.getPrice()) &&
                getState().equals(payment.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getAdvertisement(), getType(), getStartDate(), getEndDate(), getPrice(), getState());
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", user=" + user +
                ", advertisement=" + advertisement +
                ", type=" + type +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", state=" + state +
                '}';
    }
}
