package com.senlainc.javacourses.petushokvaliantsin.model.payment;

import com.senlainc.javacourses.petushokvaliantsin.model.State;
import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id", nullable = false)
    private Advertisement advertisement;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private PaymentType type;
    @Column(name = "payment_start_date")
    private LocalDate startDate;
    @Column(name = "payment_end_date")
    private LocalDate endDate;
    @Column(name = "payment_price")
    private Double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private State state;

    public Payment() {
    }

    public Payment(Advertisement advertisement, PaymentType type, LocalDate startDate, LocalDate endDate, Double price, State state) {
        this.advertisement = advertisement;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id.equals(payment.id) &&
                advertisement.equals(payment.advertisement) &&
                type.equals(payment.type) &&
                startDate.equals(payment.startDate) &&
                endDate.equals(payment.endDate) &&
                price.equals(payment.price) &&
                state.equals(payment.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, advertisement, type, startDate, endDate, price, state);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", advertisement=" + advertisement +
                ", type=" + type +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", state=" + state +
                '}';
    }
}
