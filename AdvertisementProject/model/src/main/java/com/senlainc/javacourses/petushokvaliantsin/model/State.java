package com.senlainc.javacourses.petushokvaliantsin.model;

import com.senlainc.javacourses.petushokvaliantsin.model.advertisement.Advertisement;
import com.senlainc.javacourses.petushokvaliantsin.model.payment.Payment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "states")
public class State {
    @Id
    @Column(name = "state_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long index;
    @Column(name = "state_description")
    private String description;
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
    private Set<Payment> payments;
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
    private Set<Advertisement> advertisements;

    public State() {
    }

    public State(String description) {
        this.description = description;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public Set<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(Set<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return index.equals(state.index) &&
                description.equals(state.description) &&
                Objects.equals(payments, state.payments) &&
                Objects.equals(advertisements, state.advertisements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, description, payments, advertisements);
    }

    @Override
    public String toString() {
        return "State{" +
                "index=" + index +
                ", description='" + description + '\'' +
                ", payments=" + payments +
                ", advertisements=" + advertisements +
                '}';
    }
}